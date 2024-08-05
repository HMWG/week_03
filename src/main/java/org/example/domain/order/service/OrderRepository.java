package org.example.domain.order.service;

import org.example.domain.order.OrderEntity;

import java.util.List;

public interface OrderRepository {
    public long save(OrderEntity orderEntity);

    public int delete(Long order_id);

    public int update(OrderEntity orderEntity);

    public OrderEntity findByOrderId(Long order_id);

    public List<OrderEntity> findAll();

    public int getTotalPriceByOrderId(Long order_id);

    public List<OrderEntity> findByUserId(Long user_id);

}
