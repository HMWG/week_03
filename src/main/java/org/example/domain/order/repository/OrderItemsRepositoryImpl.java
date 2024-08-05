package org.example.domain.order.repository;

import org.example.domain.order.OrderItemEntity;
import org.example.domain.order.service.OrderItemsRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.util.DBUtil.close;
import static org.example.util.DBUtil.getConnection;

public class OrderItemsRepositoryImpl implements OrderItemsRepository {


//    public static void main(String[] args) {
//        OrderItemsRepositoryImpl orderItemsRepository = new OrderItemsRepositoryImpl();
//        orderItemsRepository.save(new OrderItemEntity(2, 2, 10));
//        List<OrderItemEntity> all = orderItemsRepository.findAll();
//        System.out.println(all);
//
//        List<OrderItemEntity> byOrderId = orderItemsRepository.findByOrderId(2);
//        System.out.println(byOrderId);
//
//        List<OrderItemEntity> byProductId = orderItemsRepository.findByProductId(2);
//        System.out.println(byOrderId);
//
//        int quantityByProductIdAndOrderId = orderItemsRepository.findQuantityByProductIdAndOrderId(2, 2);
//        System.out.println(quantityByProductIdAndOrderId);
//    }
    @Override
    public int save(OrderItemEntity orderItemEntity) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result;
        String sql = "insert into order_items (order_id, product_id, quantity) values (?, ?, ?)";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, orderItemEntity.getOrder_id());
            pstmt.setLong(2, orderItemEntity.getProduct_id());
            pstmt.setLong(3, orderItemEntity.getQuantity());
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt, conn);
        }

        return result;
    }

    @Override
    public List<OrderItemEntity> findAll() {
        List<OrderItemEntity> orderItemEntities = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select order_id, product_id, quantity from order_items";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                orderItemEntities.add(new OrderItemEntity(
                        rs.getLong("order_id"),
                        rs.getLong("product_id"),
                        rs.getInt("quantity")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt, conn, rs);
        }

        return orderItemEntities;
    }

    @Override
    public List<OrderItemEntity> findByOrderId(long orderId) {
        List<OrderItemEntity> orderItemEntities = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select order_id, product_id, quantity from order_items where order_id = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, orderId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                orderItemEntities.add(new OrderItemEntity(
                        rs.getLong("order_id"),
                        rs.getLong("product_id"),
                        rs.getInt("quantity")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt, conn, rs);
        }

        return orderItemEntities;
    }

    @Override
    public List<OrderItemEntity> findByProductId(long product_id) {
        List<OrderItemEntity> orderItemEntities = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select order_id, product_id, quantity from order_items where product_id = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, product_id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                orderItemEntities.add(new OrderItemEntity(
                        rs.getLong("order_id"),
                        rs.getLong("product_id"),
                        rs.getInt("quantity")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs, pstmt, conn);
        }

        return orderItemEntities;
    }

    @Override
    public int findQuantityByProductIdAndOrderId(long product_id, long order_id) {
        int quantity = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select quantity from order_items where product_id = ? and order_id = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, product_id);
            pstmt.setLong(2, order_id);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                quantity = rs.getInt("quantity");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs, pstmt, conn);
        }

        return quantity;
    }
}
