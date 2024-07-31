package org.example.repository;

import org.example.domain.Order;

import java.util.List;

public interface OrderRepository {
    public int save(int user_id, String order_detail, String order_status);

    public int delete(int order_id);

    public int update(int order_id,int user_id, String order_detail, String order_status, int total_price);

    public Order findByOrderId(int order_id);

    public List<Order> findAll();

    public int getTotalPriceByOrderId(int order_id);

}
