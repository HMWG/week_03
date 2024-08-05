package org.example.domain.order.service;


import org.example.domain.order.OrderItemEntity;

import java.util.List;

public interface OrderItemsRepository {

    int save(OrderItemEntity orderItemEntity);
    List<OrderItemEntity> findAll();
    List<OrderItemEntity> findByOrderId(long orderId);
    List<OrderItemEntity> findByProductId(long product_id);
    int findQuantityByProductIdAndOrderId(long product_id, long order_id);
}
