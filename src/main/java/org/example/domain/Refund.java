package org.example.domain;

public class Refund {
    private Long refundId;

    private Long orderId;

    private String refundReason;

    public Refund(Long orderId, String refundReason) {
        this.orderId = orderId;
        this.refundReason = refundReason;
    }

    @Override
    public String toString() {
        return "Refund{" +
                "refundId=" + refundId +
                ", orderId=" + orderId +
                ", refundReason='" + refundReason + '\'' +
                '}';
    }

    public Long getRefundId() {
        return refundId;
    }

    public void setRefundId(Long refundId) {
        this.refundId = refundId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }
}
