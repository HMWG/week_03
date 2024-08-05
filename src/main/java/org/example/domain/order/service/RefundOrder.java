package org.example.domain.order.service;

import org.example.domain.order.OrderEntity;
import org.example.domain.order.OrderStatus;
import org.example.domain.order.repository.OrderRepositoryImpl;
import org.example.domain.user.User;
import org.example.domain.user.repository.UserRepositoryImpl;
import org.example.domain.user.service.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RefundOrder {
    OrderRepository orderRepository = new OrderRepositoryImpl();
    UserRepository userRepository = new UserRepositoryImpl();

    /**
     * 주문 완료 상태 확인
     * @param orderId 주문 ID
     * @return 결제 완료 상태일 경우 true 리턴
     */
    public boolean checkPaymentCompletion(Long orderId){
        return orderRepository.findByOrderId(orderId).getOrderStatus().equals(OrderStatus.PAY_COMPLETE);
    }

    /**
     * 환불하기
     * @param userId 회원 ID
     * @param orderId 주문 ID
     * @return 처리 메세지
     */
    public String refund(Long userId, Long orderId){
        OrderEntity order = orderRepository.findByOrderId(orderId);
        if (userId.equals(order.getUserId())) {
            if(checkPaymentCompletion(orderId)){
                OrderEntity updateOrder = new OrderEntity();
                updateOrder.setOrderId(orderId);
                updateOrder.setOrderStatus(OrderStatus.REFUND_REQUEST);
                orderRepository.update(updateOrder);
                return "결제 취소를 요청했습니다.";
            }
            return "결제를 안했거나 배송을 시작했습니다.";
        }
        return "회원님이 주문하신 주문이 아닙니다.";
    }

    /**
     * 회원의 관리자 권한 확인
     * @param userId 회원 ID
     * @return 회원이 관리자면 true
     */
    public boolean checkAdmin(Long userId){
        User user;
        if(userRepository.findById(userId).isPresent()){
            user = userRepository.findById(userId).get();
            return user.getIsAdmin();
        }
        return false;
    }

    /**
     * 환불 요청된 주문 수락 (일괄 수락)
     * @param userId 유저 ID (관리자 확인용)
     * @return 처리 메세지 list
     */
    public List<String> acceptRefund(Long userId){
        List<String> list = new ArrayList<>();
        if(checkAdmin(userId)){
            List<OrderEntity> orders = orderRepository.findAll();
            orders.stream().filter(order -> order.getOrderStatus().equals(OrderStatus.REFUND_REQUEST)).forEach(
                    order -> {order.setOrderStatus(OrderStatus.REFUND_COMPLETED);
                    orderRepository.update(order);
                    list.add(order.getOrderId()+"번 주문 환불 수락");
                    }
            );
        }
        list.add("관리자 권한이 없습니다.");
        return list;
    }
}
