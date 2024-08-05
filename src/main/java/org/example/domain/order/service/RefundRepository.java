package org.example.domain.order.service;

import org.example.domain.order.Refund;

import java.util.List;

public interface RefundRepository {
    public int save(Long orderId, String refundReason);
    public Refund findByOrderId(Long orderId);
    public List<Refund> findAllByUserId(Long userId);
    public int deleteByOrderId(Long orderId);
}
