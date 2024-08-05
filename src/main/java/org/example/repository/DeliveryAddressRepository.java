package org.example.repository;

import org.example.domain.DeliveryAddress;

import java.awt.*;
import java.util.List;

public interface DeliveryAddressRepository {
    void save(DeliveryAddress deliveryAddress);
    List<DeliveryAddress> findAll();
    List<DeliveryAddress> findByUserId(long userId);
    DeliveryAddress findByUserIdAndIsConfigured(long userId);
    DeliveryAddress findById(long id);
    void updateDeliveryAddress(long userId, String address, boolean isConfigured, Point coordinate, long deliveryAddressId);
    void deleteByUserId(long userId);
    void deleteById(long id);
}
