package org.example.domain.order.service;

import org.example.domain.order.OrderEntity;
import org.example.domain.order.OrderStatus;
import org.example.domain.order.repository.OrderRepositoryImpl;

public class CancleOrder {
    OrderRepository orderRepository = new OrderRepositoryImpl();

    /**
     * 주문 상태 확인
     * @param orderId 주문 ID
     * @return 주문이 배송 전이면 true
     */
    public boolean checkOrderStatus(Long orderId){
        OrderEntity order = orderRepository.findByOrderId(orderId);
        if (order == null) {
            return false;
        }
        return order.getOrderStatus().equals(OrderStatus.PAY_REQUEST);
    }

    /**
     * 주문 취소하기
     * @param userId 회원 ID
     * @param orderId 주문 ID
     * @return 처리 메세지
     */
    public String cancleOrder(Long userId, Long orderId) {
        OrderEntity order = orderRepository.findByOrderId(orderId);
        if (order == null) {
            return "해당 주문은 존재하지 않습니다.";
        }
        if (userId.equals(order.getUserId())) {
            if(checkOrderStatus(orderId)){
                OrderEntity updateOrder = new OrderEntity();
                updateOrder.setOrderId(orderId);
                updateOrder.setOrderStatus(OrderStatus.ORDER_CANCELED);
                return "주문을 취소했습니다.";
            }
            return "주문을 취소할 수 없습니다.";
        }
        return "회원님이 주문하신 주문이 아닙니다.";
    }
}
