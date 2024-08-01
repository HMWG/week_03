package org.example.service;

import org.example.domain.Order;
import org.example.repository.OrderRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class OrderService implements OrderRepository {

    public OrderService() {
    }

    @Override
    public int save(int user_id, String order_detail, String order_status) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String SQL;
        String SQL2;
        ResultSet rs = null;
        int order_id = 0;

        try {
            conn = DBUtil.getConnection();
            SQL = "insert into orders(order_detail, user_id, total_price, order_status) values(?,?,?,?)";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, order_detail);
            pstmt.setInt(2, user_id);
            pstmt.setInt(3, 0);
            pstmt.setString(4, order_status);
            int result = pstmt.executeUpdate();
            SQL2 = "select order_id from orders order by order_id desc limit 1";
            pstmt = conn.prepareStatement(SQL2);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                order_id = rs.getInt("order_id");
            }
            return order_id;
        } catch (SQLException e) {
            System.out.println(1);
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(rs, pstmt, conn);
        }
    }

    @Override
    public int delete(int order_id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String SQL;

        try {
            conn = DBUtil.getConnection();
            SQL = "DELETE FROM orders where order_id = ?";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, order_id);
            int result = pstmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(pstmt, conn);
        }
    }

    @Override
    public int update(int order_id,int user_id, String order_detail, String order_status, int total_price) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String SQL;

        try {
            conn = DBUtil.getConnection();
            SQL = "update orders set user_id = ?, order_detail = ?, order_status = ?, total_price = ? Where order_id = ?";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, user_id);
            pstmt.setString(2, order_detail);
            pstmt.setString(3, order_status);
            pstmt.setInt(4, total_price);
            pstmt.setInt(5, order_id);
            int result = pstmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(pstmt, conn);
        }
    }

    @Override
    public Order findByOrderId(int order_id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String SQL;
        Order order = null;

        try {
            conn = DBUtil.getConnection();
            SQL = "select * from orders where order_id = ?";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, order_id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                order = new Order(rs.getLong("order_id"), rs.getLong("user_id"), rs.getString("order_detail"), rs.getInt("total_price"), rs.getString("order_status"), (LocalDateTime) rs.getObject("created_at"));
            }
            return order;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(rs, pstmt, conn);
        }
    }

    @Override
    public List<Order> findAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String SQL;
        List<Order> orders = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            SQL = "select * from orders";
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
            while (rs.next()){
                orders.add(new Order(rs.getLong("order_id"), rs.getLong("user_id"), rs.getString("order_detail"), rs.getInt("total_price"), rs.getString("order_status"), (LocalDateTime) rs.getObject("created_at")));
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(rs, pstmt, conn);
        }
    }

    @Override
    public int getTotalPriceByOrderId(int order_id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int total_price = 0;
        try {
            //conn = DriverManager.getConnection(url, user, password);
            conn = DBUtil.getConnection();
            String SQL = "select sum(price) as total_price from (products p join order_items i on p.product_id = i.product_id) join orders o on i.order_id = o.order_id  group by o.order_id having o.order_id = ?";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, order_id);
            rs = pstmt.executeQuery();
            if (rs.next()){
                total_price = rs.getInt(1);
            }
            return total_price;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(rs, pstmt, conn);
        }
    }
}
