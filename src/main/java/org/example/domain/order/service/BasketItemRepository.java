package org.example.domain.order.service;

import java.util.List;
import org.example.domain.order.BasketItem;

public interface BasketItemRepository {

    void save(BasketItem basketItem);

    List<BasketItem> findByUserId(Long userId);

    void increaseQuantity(Long userId, Long productId);

    void decreaseQuantity(Long userId, Long productId);

    void delete(Long userId, Long productId);

}
