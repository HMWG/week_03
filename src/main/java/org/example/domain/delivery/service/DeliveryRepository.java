package org.example.domain.delivery.service;

import org.example.domain.delivery.Delivery;

import java.util.List;
import java.util.Optional;

public interface DeliveryRepository {

    void save(Delivery delivery);

    List<Delivery> findAll();

    Optional<Delivery> findByDeliveryId(long deliveryId);

    Optional<Delivery> findByOrderId(long orderId);

    Optional<Delivery> findByDeliverAddressId(long deliveryAddressId);

}
