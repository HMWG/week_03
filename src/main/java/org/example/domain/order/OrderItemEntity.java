package org.example.domain.order;

public class OrderItemEntity {

    private long order_id;
    private long product_id;
    private int quantity;

    public OrderItemEntity(long order_id, long product_id, int quantity) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public static OrderItemEntity create(long order_id, BasketItem basketItem) {
        return new OrderItemEntity(order_id, basketItem.product().productId(),
                basketItem.quantity());
    }

    public long getOrder_id() {
        return order_id;
    }

    public long getProduct_id() {
        return product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "OrderItemEntity{" +
                "order_id=" + order_id +
                ", product_id=" + product_id +
                ", quantity=" + quantity +
                '}';
    }
}
