package org.example.domain.order.service;

import java.util.List;
import org.example.domain.order.Basket;
import org.example.domain.order.BasketItem;
import org.example.domain.order.BasketItemEntity;
import org.example.domain.order.repository.BasketItemRepositoryImpl;
import org.example.domain.product.repository.ProductRepositoryImpl;
import org.example.domain.product.service.ProductRepository;
import org.example.domain.user.User;

public class BasketItemService {

    private final BasketItemRepository basketItemRepository = new BasketItemRepositoryImpl();
    private final ProductRepository productRepository = new ProductRepositoryImpl();

    public void addBasketItem(User user, long productId, int quantity) {
        BasketItemEntity basketItemEntity = BasketItemEntity.of(user.getUserId(), productId, quantity);
        basketItemRepository.save(basketItemEntity);
    }

    public Basket getBasket(long userId) {
        List<BasketItemEntity> basketItemEntities = basketItemRepository.findByUserId(userId);

        List<BasketItem> basketItems = basketItemEntities.stream()
                .map(basketItemEntity -> BasketItem.of(
                        productRepository.findById(basketItemEntity.productId()),
                        basketItemEntity.quantity()
                )).toList();

        return Basket.from(basketItems);
    }

}
