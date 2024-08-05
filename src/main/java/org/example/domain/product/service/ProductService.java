package org.example.domain.product.service;

import org.example.domain.order.Order;
import org.example.domain.order.OrderStatus;
import org.example.domain.order.repository.OrderRepositoryImpl;
import org.example.domain.order.service.OrderRepository;
import org.example.domain.product.Product;
import org.example.domain.product.repository.ProductRepositoryImpl;

import java.util.List;

public class ProductService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public ProductService() {
        this.productRepository = new ProductRepositoryImpl();
        this.orderRepository = new OrderRepositoryImpl();
    }

    public void registerProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(long productId) {
        productRepository.deleteById(productId);
    }

    public List<Order> getPayCompleteOrders() {
        return orderRepository.findAll().stream()
                .filter(o -> o.getOrderStatus().equals(OrderStatus.PAY_COMPLETE)).toList();
    }

    public void acceptDelivery(List<Order> payCompleteOrders, Integer index) {
        Order order = payCompleteOrders.get(index);
        order.setOrderStatus(OrderStatus.SHIPPING);
        orderRepository.update(order);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
}
