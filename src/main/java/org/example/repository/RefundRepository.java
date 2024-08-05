package org.example.repository;

import org.example.domain.Refund;

import java.util.List;

public interface RefundRepository {
    public int save(Long orderId, String refundReason);
    public Refund findByOrderId(Long orderId);
    public List<Refund> findAllByUserId(Long userId);
    public int deleteByOrderId(Long orderId);
}
