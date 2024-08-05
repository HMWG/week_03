package org.example.domain.delivery.repository;

import org.example.domain.delivery.DeliveryAddress;
import org.example.domain.delivery.service.DeliveryAddressRepository;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.example.util.DBUtil;

public class DeliveryAddressRepositoryImpl implements DeliveryAddressRepository {
    @Override
    public void save(DeliveryAddress deliveryAddress) {
        Connection connection = null;
        PreparedStatement psmt = null;
        String sql = "INSERT INTO delivery_address (user_id, address, is_configured, coordinate) values (?, ?, ?, ST_PointFromText(?))";

        try {
            connection = DBUtil.getConnection();
            psmt = connection.prepareStatement(sql);
            psmt.setLong(1, deliveryAddress.getUserId());
            psmt.setString(2, deliveryAddress.getAddress());
            psmt.setBoolean(3, deliveryAddress.getIsConfigured());
            psmt.setString(4, "POINT(" + deliveryAddress.getCoordinate().getX() + "," + deliveryAddress.getCoordinate().getY() + ")");
            psmt.executeUpdate();
            System.out.println("배송지 등록 성공");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DeliveryAddress> findAll() {
        String sql = "SELECT * FROM delivery_address";
        Connection connection = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            connection = DBUtil.getConnection();
            psmt = connection.prepareStatement(sql);
            rs = psmt.executeQuery();
            List<DeliveryAddress> deliveryAddressList = new ArrayList<>();
            while (rs.next()) {
                deliveryAddressList.add(getDeliveryAddress(rs));
            }
            return deliveryAddressList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DeliveryAddress> findByUserId(long userId) {
        Connection connection = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM delivery_address WHERE user_id = ?";

        try {
            connection = DBUtil.getConnection();
            psmt = connection.prepareStatement(sql);
            psmt.setLong(1, userId);
            rs = psmt.executeQuery();
            List<DeliveryAddress> deliveryAddressList = new ArrayList<>();
            while (rs.next()) {
                deliveryAddressList.add(getDeliveryAddress(rs));
            }
            return deliveryAddressList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DeliveryAddress findByUserIdAndIsConfigured(long userId) {
        Connection connection = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM delivery_address WHERE user_id = ? AND is_configured = ?";

        try {
            connection = DBUtil.getConnection();
            psmt = connection.prepareStatement(sql);
            psmt.setLong(1, userId);
            psmt.setBoolean(2, true);
            rs = psmt.executeQuery();

            if (rs.next()) {
                return getDeliveryAddress(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DeliveryAddress findById(long id) {
        Connection connection = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM delivery_address WHERE delivery_address_id = ?";

        try {
            connection = DBUtil.getConnection();
            psmt = connection.prepareStatement(sql);
            psmt.setLong(1, id);
            rs = psmt.executeQuery();

            if (rs.next()) {
                return getDeliveryAddress(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateDeliveryAddress(long userId, String address, boolean isConfigured, Point coordinate, long deliveryAddressId) {
        Connection connection = null;
        PreparedStatement psmt = null;
        String sql = "UPDATE delivery_address SET user_id = ?, address = ?, is_configured = ?, coordinate = ST_PointFromText(?) WHERE delivery_address_id = ?";

        try {
            connection = DBUtil.getConnection();
            psmt = connection.prepareStatement(sql);
            psmt.setLong(1, userId);
            psmt.setString(2, address);
            psmt.setBoolean(3, isConfigured);
            psmt.setString(4, "POINT(" + coordinate.getX() + "," + coordinate.getY() + ")");
            psmt.setLong(5, deliveryAddressId);
            psmt.executeUpdate();
            System.out.println("배송지 수정 성공");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteByUserId(long userId) {
        Connection connection = null;
        PreparedStatement psmt = null;
        String sql = "DELETE FROM delivery_address WHERE user_id = ?";

        try {
            connection = DBUtil.getConnection();
            psmt = connection.prepareStatement(sql);
            psmt.setLong(1, userId);
            psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(long id) {
        Connection connection = null;
        PreparedStatement psmt = null;
        String sql = "DELETE FROM delivery_address WHERE delivery_address_id = ?";

        try {
            connection = DBUtil.getConnection();
            psmt = connection.prepareStatement(sql);
            psmt.setLong(1, id);
            psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private DeliveryAddress getDeliveryAddress(ResultSet rs) throws SQLException {
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setDeliveryAddressId(rs.getLong(1));
        deliveryAddress.setUserId(rs.getLong(2));
        deliveryAddress.setAddress(rs.getString(3));
        deliveryAddress.setIsConfigured(rs.getBoolean(4));
        deliveryAddress.setCoordinate(rs.getObject(5, Point.class));

        return deliveryAddress;
    }
}
