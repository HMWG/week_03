package org.example.domain.order.service;

import org.example.domain.order.OrderEntity;
import org.example.domain.order.OrderStatus;
import org.example.domain.order.repository.OrderRepositoryImpl;

public class PaymentOrder {
    OrderRepository orderRepository = new OrderRepositoryImpl();

    /**
     * 결제 요청 상태 확인
     * @param orderId 주문 ID
     * @return 결제 요청 상태일 경우 true 리턴
     */
    public boolean checkPaymentRequest(Long orderId){
        OrderEntity order = orderRepository.findByOrderId(orderId);
        if (order == null) {
            return false;
        }
        return orderRepository.findByOrderId(orderId).getOrderStatus().equals(OrderStatus.PAY_REQUEST);
    }

    /**
     * 결제하기
     * @param orderId 주문 ID
     * @param userId 회원 ID
     * @return 처리 메세지
     */
    public String pay(Long userId, Long orderId){
        OrderEntity order = orderRepository.findByOrderId(orderId);
        if (order == null) {
            return "해당 주문은 존재하지 않습니다.";
        }
        if (userId.equals(order.getUserId())) {
            if(checkPaymentRequest(orderId)){
                OrderEntity updateOrder = new OrderEntity();
                updateOrder.setOrderId(orderId);
                updateOrder.setOrderStatus(OrderStatus.PAY_COMPLETE);
                orderRepository.update(updateOrder);
                return "결제를 완료했습니다.";
            }
            return "결제를 완료했거나 결제가 불가능한 상태입니다.";
        }
        return "회원님이 주문하신 주문이 아닙니다.";
    }
}
