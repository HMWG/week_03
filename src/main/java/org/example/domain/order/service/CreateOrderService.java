package org.example.domain.order.service;

import java.math.BigDecimal;
import java.util.List;
import org.example.domain.order.Basket;
import org.example.domain.order.BasketItemEntity;
import org.example.domain.order.OrderEntity;
import org.example.domain.order.OrderItemEntity;
import org.example.domain.order.repository.OrderItemsRepositoryImpl;
import org.example.domain.order.repository.OrderRepositoryImpl;
import org.example.domain.product.Product;
import org.example.domain.user.User;

public class CreateOrderService {

    private final OrderRepository orderRepository = new OrderRepositoryImpl();
    private final OrderItemsRepository orderItemsRepository = new OrderItemsRepositoryImpl();


    OrderEntity createOrder(User user, Product product, int quantity, String orderDetails) {
        return OrderEntity.create(user.getUserId(), orderDetails, calculateTotalPrice(product, quantity));
    }

    void createOrder(User user, Basket basket, String orderDetails) {
        OrderEntity orderEntity = OrderEntity.create(user.getUserId(), orderDetails, basket.totalPrice());
        long orderId = orderRepository.save(orderEntity);
        System.out.println("주문이 생성되었습니다 - orderId : " + orderId);
        basket.basketItems().forEach(
                basketItem -> orderItemsRepository.save(OrderItemEntity.create(orderId, basketItem)));
        System.out.println("주문 상품들이 정상적으로 생성되었습니다 - orderId : " + orderId);
    }

    private static Product getBasketProduct(BasketItemEntity basketItemEntity, List<Product> basketProducts) {
        return basketProducts.stream()
                .filter(product -> product.productId().equals(basketItemEntity.productId()))
                .findFirst()
                .orElseThrow();
    }

    public int calculateTotalPrice(Product product, int quantity) {
        return product.price().multiply(BigDecimal.valueOf(quantity)).intValue();
    }
}