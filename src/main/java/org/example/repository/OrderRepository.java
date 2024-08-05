package org.example.repository;

import org.example.domain.Order;
import org.example.domain.OrderStatus;

import java.util.List;

public interface OrderRepository {
    public int save(Long user_id, String order_detail, OrderStatus order_status);

    public int delete(Long order_id);

    public int update(Order order);

    public Order findByOrderId(Long order_id);

    public List<Order> findAll();

    public int getTotalPriceByOrderId(Long order_id);

    public List<Order> findByUserId(Long user_id);

}
