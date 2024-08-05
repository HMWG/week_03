package org.example.repositoryImpl;

import org.example.domain.OrderItem;
import org.example.repository.OrderItemsRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.repositoryImpl.DBUtil.close;
import static org.example.repositoryImpl.DBUtil.getConnection;

public class OrderItemsRepositoryImpl implements OrderItemsRepository {


//    public static void main(String[] args) {
//        OrderItemsRepositoryImpl orderItemsRepository = new OrderItemsRepositoryImpl();
//        orderItemsRepository.save(new OrderItem(2, 2, 10));
//        List<OrderItem> all = orderItemsRepository.findAll();
//        System.out.println(all);
//
//        List<OrderItem> byOrderId = orderItemsRepository.findByOrderId(2);
//        System.out.println(byOrderId);
//
//        List<OrderItem> byProductId = orderItemsRepository.findByProductId(2);
//        System.out.println(byOrderId);
//
//        int quantityByProductIdAndOrderId = orderItemsRepository.findQuantityByProductIdAndOrderId(2, 2);
//        System.out.println(quantityByProductIdAndOrderId);
//    }
    @Override
    public int save(OrderItem orderItem) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result;
        String sql = "insert into order_items (order_id, product_id, quantity) values (?, ?, ?)";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, orderItem.getOrder_id());
            pstmt.setLong(2, orderItem.getProduct_id());
            pstmt.setLong(3, orderItem.getQuantity());
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt, conn);
        }

        return result;
    }

    @Override
    public List<OrderItem> findAll() {
        List<OrderItem> orderItems = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select order_id, product_id, quantity from order_items";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                orderItems.add(new OrderItem(
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

        return orderItems;
    }

    @Override
    public List<OrderItem> findByOrderId(long orderId) {
        List<OrderItem> orderItems = new ArrayList<>();
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
                orderItems.add(new OrderItem(
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

        return orderItems;
    }

    @Override
    public List<OrderItem> findByProductId(long product_id) {
        List<OrderItem> orderItems = new ArrayList<>();
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
                orderItems.add(new OrderItem(
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

        return orderItems;
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
