package org.example.repository;


import org.example.domain.OrderItem;

import java.util.List;

public interface OrderItemsRepository {

    int save(OrderItem orderItem);
    List<OrderItem> findAll();
    List<OrderItem> findByOrderId(long orderId);
    List<OrderItem> findByProductId(long product_id);
}
