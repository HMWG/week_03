package org.example.domain;

public class OrderItem {
    private long order_id;
    private long product_id;
    private int quantity;

    public OrderItem(long order_id, long product_id, int quantity) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
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
}
