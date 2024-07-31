package org.example.domain;

import java.time.LocalDateTime;

public class Order {
    private long order_id;

    private long user_id;

    private String order_detail;

    private int total_price;

    private String order_status;

    private LocalDateTime created_at;

    public Order(long order_id, long user_id, String order_detail, int total_price, String order_status, LocalDateTime created_at) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.order_detail = order_detail;
        this.total_price = total_price;
        this.order_status = order_status;
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", user_id=" + user_id +
                ", order_detail='" + order_detail + '\'' +
                ", total_price=" + total_price +
                ", order_status='" + order_status + '\'' +
                ", created_at=" + created_at +
                '}';
    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getOrder_detail() {
        return order_detail;
    }

    public void setOrder_detail(String order_detail) {
        this.order_detail = order_detail;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}
