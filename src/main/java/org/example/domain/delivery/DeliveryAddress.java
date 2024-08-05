package org.example.domain.delivery;

import java.awt.Point;
import org.example.domain.user.User;

public class DeliveryAddress {
    private long deliveryAddressId;
    private long userId;
    private String address;
    private boolean isConfigured;
    private Point coordinate;

    public DeliveryAddress() {}

    public static DeliveryAddress of(String address, boolean isConfigured, Point coordinate) {
        return new DeliveryAddress(address, isConfigured, coordinate);
    }

    public static DeliveryAddress of(User user, String address, boolean isConfigured, Point coordinate) {
        return new DeliveryAddress(user, address, isConfigured, coordinate);
    }

    private DeliveryAddress(String address, boolean isConfigured, Point coordinate) {
        this.address = address;
        this.isConfigured = isConfigured;
        this.coordinate = coordinate;
    }

    private DeliveryAddress(User user, String address, boolean isConfigured, Point coordinate) {
        this.userId = user.getUserId();
        this.address = address;
        this.isConfigured = isConfigured;
        this.coordinate = coordinate;
    }

    public void setDelivery_address_id(long deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setIsConfigured(boolean isConfigured) {
        this.isConfigured = isConfigured;
    }

    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }

    public long getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public long getUserId() {
        return userId;
    }

    public String getAddress() {
        return address;
    }

    public boolean getIsConfigured() {
        return isConfigured;
    }

    public Point getCoordinate() {
        return coordinate;
    }
}
