package org.example.domain;

public class OrderItem {
    private long order_id;
    private long product_id;

    public OrderItem(long order_id, long product_id) {
        this.order_id = order_id;
        this.product_id = product_id;
    }

    public long getOrder_id() {
        return order_id;
    }

    public long getProduct_id() {
        return product_id;
    }
}
