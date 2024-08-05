package org.example.domain.delivery;

public class Delivery {
    long delivery_id;
    long delivery_address_id;
    long order_id;

    public Delivery(long delivery_id, long delivery_address_id, long order_id) {
        this.delivery_id = delivery_id;
        this.delivery_address_id = delivery_address_id;
        this.order_id = order_id;
    }

    public long getDelivery_id() {
        return delivery_id;
    }

    public long getDelivery_address_id() {
        return delivery_address_id;
    }

    public long getOrder_id() {
        return order_id;
    }
}
