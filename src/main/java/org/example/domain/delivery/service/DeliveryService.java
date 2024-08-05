package org.example.domain.delivery.service;

import java.util.List;
import org.example.domain.delivery.Delivery;
import org.example.domain.delivery.DeliveryAddress;

public class DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final DeliveryAddressRepository deliveryAddressRepository;

    public DeliveryService(DeliveryRepository deliveryRepository, DeliveryAddressRepository deliveryAddressRepository) {
        this.deliveryRepository = deliveryRepository;
        this.deliveryAddressRepository = deliveryAddressRepository;
    }

    public String findAddressByOrderId(Long deliveryId) {
        Delivery delivery = deliveryRepository.findByDeliveryId(deliveryId)
                .orElseThrow(() -> new IllegalArgumentException("배송 정보가 없습니다."));
        DeliveryAddress deliveryAddress = deliveryAddressRepository.findById(delivery.getDelivery_address_id());

        return deliveryAddress.getAddress();
    }

    public List<DeliveryAddress> findByUserId(Long userId) {
        return deliveryAddressRepository.findByUserId(userId);
    }
}
