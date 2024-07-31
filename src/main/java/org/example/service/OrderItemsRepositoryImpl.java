package org.example.service;

import org.example.domain.OrderItem;
import org.example.repository.OrderItemsRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemsRepositoryImpl implements OrderItemsRepository {

    @Override
    public int save(OrderItem orderItem) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result;
        String sql = "insert into order_items (order_id, product_id) values (?, ?)";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, orderItem.getOrder_id());
            pstmt.setLong(2, orderItem.getProduct_id());
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return result;
    }

    @Override
    public List<OrderItem> findAll() {
        List<OrderItem> orderItems = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select order_id, product_id from order_items";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                orderItems.add(new OrderItem(rs.getLong("order_id"), rs.getLong("product_id")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return orderItems;
    }

    @Override
    public List<OrderItem> findByOrderId(long orderId) {
        List<OrderItem> orderItems = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select order_id, product_id from order_items where order_id = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, orderId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                orderItems.add(new OrderItem(rs.getLong("order_id"), rs.getLong("product_id")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return orderItems;
    }

    @Override
    public List<OrderItem> findByProductId(long product_id) {
        List<OrderItem> orderItems = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select order_id, product_id from order_items where product_id = ?";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, product_id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                orderItems.add(new OrderItem(rs.getLong("order_id"), rs.getLong("product_id")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return orderItems;
    }


    private Connection getConnection() throws SQLException {
        Connection conn = null;
        String dbPassword = System.getenv("DB_PASSWORD");
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + System.getenv("DB_SCHEMA"), System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
//            conn = DriverManager.getConnection("jdbc:mysql://59.27.84.200:3306/workshop", "grepp", "grepp");
        return conn;
    }
}
