package org.example.domain.order;

import java.time.LocalDateTime;

public class OrderEntity {
    private Long orderId;

    private Long userId;

    private String orderDetail;

    private int totalPrice;

    private OrderStatus orderStatus;

    private LocalDateTime createdAt;

    public OrderEntity(Long orderId, Long userId, String orderDetail, int totalPrice, OrderStatus orderStatus, LocalDateTime createdAt) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDetail = orderDetail;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
    }

    private OrderEntity(Long userId, String orderDetail, int totalPrice, OrderStatus orderStatus) {
        this.userId = userId;
        this.orderDetail = orderDetail;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
    }

    public static OrderEntity create(Long userId, String orderDetail, int totalPrice) {
        return new OrderEntity(
                userId,
                orderDetail,
                totalPrice,
                OrderStatus.PAY_REQUEST
        );
    }

    public OrderEntity() {
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", orderDetail='" + orderDetail + '\'' +
                ", totalPrice=" + totalPrice +
                ", orderStatus=" + orderStatus +
                ", createdAt=" + createdAt +
                '}';
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(String orderDetail) {
        this.orderDetail = orderDetail;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
