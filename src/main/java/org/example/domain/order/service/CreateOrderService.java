package org.example.domain.order.service;

import java.math.BigDecimal;
import java.util.List;
import org.example.domain.order.Basket;
import org.example.domain.order.BasketItem;
import org.example.domain.order.BasketItemEntity;
import org.example.domain.order.OrderEntity;
import org.example.domain.order.OrderItemEntity;
import org.example.domain.order.repository.OrderItemsRepositoryImpl;
import org.example.domain.order.repository.OrderRepositoryImpl;
import org.example.domain.product.Product;
import org.example.domain.product.repository.ProductRepositoryImpl;
import org.example.domain.product.service.ProductRepository;
import org.example.domain.user.User;

public class CreateOrderService {

    private final OrderRepository orderRepository = new OrderRepositoryImpl();
    private final OrderItemsRepository orderItemsRepository = new OrderItemsRepositoryImpl();
    private final ProductRepository productRepository = new ProductRepositoryImpl();


    public OrderEntity createOrder(User user, long productId, int quantity, String orderDetails) {
        Product orderProduct = productRepository.findById(productId);
        OrderEntity orderEntity = OrderEntity.create(user.getUserId(), orderDetails,
                calculateTotalPrice(orderProduct, quantity));
        long orderId = orderRepository.save(orderEntity);
        orderItemsRepository.save(OrderItemEntity.create(orderId, BasketItem.of(orderProduct, quantity)));
        productRepository.decreaseQuantity(orderProduct, quantity);
        orderEntity.setOrderId(orderId);
        return orderEntity;
    }

    public void createOrder(User user, Basket basket, String orderDetails) {
        OrderEntity orderEntity = OrderEntity.create(user.getUserId(), orderDetails, basket.totalPrice());
        long orderId = orderRepository.save(orderEntity);
        System.out.println("주문이 생성되었습니다 - orderId : " + orderId);
        basket.basketItems().forEach(
                basketItem -> {
                    orderItemsRepository.save(OrderItemEntity.create(orderId, basketItem));
                    productRepository.decreaseQuantity(basketItem.product(), basketItem.quantity());
                });
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
