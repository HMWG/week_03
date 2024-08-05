package org.example.repositoryImpl;

import org.example.domain.Delivery;
import org.example.domain.OrderItem;
import org.example.repository.DeliveryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.example.repositoryImpl.DBUtil.close;
import static org.example.repositoryImpl.DBUtil.getConnection;

public class DeliveryRepositoryImpl implements DeliveryRepository {

    public static void main(String[] args) {
        DeliveryRepositoryImpl deliveryRepository = new DeliveryRepositoryImpl();
        deliveryRepository.save(new Delivery(1, 1, 1));
    }
    @Override
    public void save(Delivery delivery) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "insert into delivery (delivery_address_id, order_id) values (?, ?)";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, delivery.getOrder_id());
            pstmt.setLong(2, delivery.getOrder_id());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt, conn);
        }
    }

    @Override
    public List<Delivery> findAll() {
        List<Delivery> deliveries = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select delivery_id, delivery_address_id, order_id from delivery";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                deliveries.add(new Delivery(rs.getLong("deliver_id"), rs.getLong("delivery_address_id"), rs.getLong("order_id")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs, pstmt, conn);
        }

        return deliveries;
    }

    @Override
    public Optional<Delivery> findByDeliveryId(long deliveryId) {
        Optional<Delivery> deliveryOptional;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select delivery_id, delivery_address_id, order_id from delivery where delivery_id = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, deliveryId);
            rs = pstmt.executeQuery();
            deliveryOptional =
                    Optional.of(new Delivery(rs.getLong("deliver_id"), rs.getLong("delivery_address_id"), rs.getLong("order_id")));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt, conn);
        }

        return deliveryOptional;
    }

    @Override
    public Optional<Delivery> findByOrderId(long orderId) {
        Optional<Delivery> deliveryOptional;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select delivery_id, delivery_address_id, order_id from delivery where order_id = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, orderId);
            rs = pstmt.executeQuery();
            deliveryOptional =
                    Optional.of(new Delivery(rs.getLong("deliver_id"), rs.getLong("delivery_address_id"), rs.getLong("order_id")));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt, conn);
        }

        return deliveryOptional;
    }

    @Override
    public Optional<Delivery> findByDeliverAddressId(long deliveryAddressId) {
        Optional<Delivery> deliveryOptional;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select delivery_id, delivery_address_id, order_id from delivery where delivery_address_id = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, deliveryAddressId);
            rs = pstmt.executeQuery();
            deliveryOptional =
                    Optional.of(new Delivery(rs.getLong("deliver_id"), rs.getLong("delivery_address_id"), rs.getLong("order_id")));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt, conn);
        }

        return deliveryOptional;
    }
}
