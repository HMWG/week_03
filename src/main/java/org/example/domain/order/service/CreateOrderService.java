package org.example.domain.order.service;

import java.math.BigDecimal;
import java.util.List;
import org.example.domain.delivery.Delivery;
import org.example.domain.delivery.repository.DeliveryAddressRepositoryImpl;
import org.example.domain.delivery.repository.DeliveryRepositoryImpl;
import org.example.domain.delivery.service.DeliveryRepository;
import org.example.domain.order.Basket;
import org.example.domain.order.BasketItem;
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
    private final DeliveryRepository deliveryRepository = new DeliveryRepositoryImpl();


    public OrderEntity createOrder(User user, long productId, int quantity, String orderDetails, long deliveryAddressId) {
        Product orderProduct = productRepository.findById(productId);
        OrderEntity orderEntity = OrderEntity.create(user.getUserId(), orderDetails,
                calculateTotalPrice(orderProduct, quantity));
        long orderId = orderRepository.save(orderEntity);
        orderEntity.setOrderId(orderId);
        orderItemsRepository.save(OrderItemEntity.create(orderId, BasketItem.of(orderProduct, quantity)));
        productRepository.decreaseQuantity(orderProduct, quantity);
        deliveryRepository.save(Delivery.create(orderId, deliveryAddressId));
        return orderEntity;
    }

    public void createOrder(User user, Basket basket, String orderDetails, long deliveryAddressId) {
        OrderEntity orderEntity = OrderEntity.create(user.getUserId(), orderDetails, basket.totalPrice());
        long orderId = orderRepository.save(orderEntity);
        deliveryRepository.save(Delivery.create(orderId, deliveryAddressId));
        System.out.println("주문이 생성되었습니다 - orderId : " + orderId);
        basket.basketItems().forEach(
                basketItem -> {
                    orderItemsRepository.save(OrderItemEntity.create(orderId, basketItem));
                    productRepository.decreaseQuantity(basketItem.product(), basketItem.quantity());
                });

        System.out.println("주문 상품들이 정상적으로 생성되었습니다 - orderId : " + orderId);
    }

    public int calculateTotalPrice(Product product, int quantity) {
        return product.price().multiply(BigDecimal.valueOf(quantity)).intValue();
    }
}
