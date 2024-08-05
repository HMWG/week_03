package org.example.domain.order.service;

import org.example.domain.order.OrderEntity;
import org.example.domain.order.repository.OrderRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class CheckOrderStatus {
    OrderRepository orderRepository = new OrderRepositoryImpl();

    /**
     * 주문 상태 조회
     * @param orderId 주문 ID
     * @return 주문 상태
     */
    public String checkStatus(Long orderId) {
        OrderEntity order = orderRepository.findByOrderId(orderId);
        if (order == null) {
            return "해당 주문은 존재하지 않습니다.";
        }
        return "주문 상태" + order.getOrderStatus().toString();
    }

    /**
     * 주문 상세 정보 조회
     * @param orderId 주문 ID
     * @return 주문 상세정보
     */
    public String checkDetail(Long orderId) {
        OrderEntity order = orderRepository.findByOrderId(orderId);
        if (order == null) {
            return "해당 주문은 존재하지 않습니다.";
        }
        return "주문 상세 정보" + order.getOrderDetail();
    }

    /**
     * 주문 총 가격 조회
     * @param orderId 주문 ID
     * @return 주문 총 가격
     */
    public String checkTotalPrice(Long orderId){
        OrderEntity order = orderRepository.findByOrderId(orderId);
        if (order == null) {
            return "해당 주문은 존재하지 않습니다.";
        }
        return "주문 총 가격 : " + order.getTotalPrice();
    }

    /**
     * 주문 생성 일시 조회
     * @param orderId 주문 ID
     * @return 주문 생성 일시
     */
    public String checkCreatedAt(Long orderId){
        OrderEntity order = orderRepository.findByOrderId(orderId);
        if (order == null) {
            return "해당 주문은 존재하지 않습니다.";
        }
        return "주문 생성일 : " + order.getCreatedAt().toString();
    }

    /**
     * 유저 주문 리스트 조회
     * @param userId 회원 ID
     * @return 주문 리스트 정보 List
     */
    public List<String> checkOrderByUserId(Long userId){
        List<String> list = new ArrayList<>();
        List<OrderEntity> orders = orderRepository.findByUserId(userId);
        for (OrderEntity order : orders) {
            StringBuilder sb = new StringBuilder();
            sb.append("주문 번호 : ").append(order.getOrderId())
                    .append("\n주문 상태 : ").append(order.getOrderStatus())
                    .append("\n상세 내역 : ").append(order.getOrderDetail())
                    .append("\n총 가격 : ").append(order.getTotalPrice())
                    .append("\n생성 일시 : ").append(order.getCreatedAt());
            list.add(sb.toString());
        }
        return list;
    }
}
