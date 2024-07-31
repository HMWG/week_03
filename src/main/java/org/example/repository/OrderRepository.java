package org.example.repository;



public interface OrderRepository {
    public void save();

    public void delete(int order_id);

    public void update(int order_id);

    public void findByOrderId();

    public void findAll();

    public void findByTotalPrice(int total_price);
}
