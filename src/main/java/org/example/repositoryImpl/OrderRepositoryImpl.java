package org.example.repositoryImpl;

import org.example.domain.Order;
import org.example.domain.OrderStatus;
import org.example.repository.OrderRepository;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    public OrderRepositoryImpl() {
    }

    @Override
    public int save(Long user_id, String order_detail, OrderStatus order_status) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String SQL;
        String SQL2;
        int order_id = 0;

        try {
            conn = DBUtil.getConnection();
            SQL = "insert into orders(order_detail, user_id, total_price, order_status) values(?,?,?,?)";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, order_detail);
            pstmt.setLong(2, user_id);
            pstmt.setInt(3, getTotalPriceByOrderId(user_id));
            pstmt.setString(4, String.valueOf(order_status));
            int result = pstmt.executeUpdate();
            SQL2 = "select order_id from orders order by order_id desc limit 1";
            pstmt = conn.prepareStatement(SQL2);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                order_id = rs.getInt("order_id");
            }
            return order_id;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(pstmt, conn);
        }
    }

    @Override
    public int delete(Long order_id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String SQL;

        try {
            conn = DBUtil.getConnection();
            SQL = "DELETE FROM orders where order_id = ?";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setLong(1, order_id);
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
    public int update(Order order) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String SQL;

        try {
            conn = DBUtil.getConnection();
            SQL = "UPDATE orders set";
            if (order.getOrderDetail() != null && !order.getOrderDetail().isEmpty()) {
                SQL += " order_detail = '" + order.getOrderDetail() + "', ";
            }
            if (order.getOrderDetail() != null && !order.getOrderDetail().isEmpty()) {
                SQL += " order_detail = '" + order.getOrderDetail() + "', ";
            }
            if (order.getOrderStatus() != null && !order.getOrderStatus().toString().isEmpty()) {
                SQL += " order_status = '" + order.getOrderStatus() + "', ";
            }
            SQL += " total_price = ? ";
            SQL += "where order_id = " + order.getOrderId();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1,getTotalPriceByOrderId(order.getOrderId()));
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(pstmt, conn);
        }
    }

    @Override
    public Order findByOrderId(Long order_id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String SQL;

        Order order = null;

        try {
            conn = DBUtil.getConnection();
            SQL = "select * from orders where order_id = ?";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setLong(1, order_id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                order = new Order(rs.getLong("order_id"), rs.getLong("user_id"), rs.getString("order_detail"), rs.getInt("total_price"), OrderStatus.valueOf(rs.getString("order_status")), (LocalDateTime) rs.getObject("created_at"));
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
                orders.add(new Order(rs.getLong("order_id"), rs.getLong("user_id"), rs.getString("order_detail"), rs.getInt("total_price"), OrderStatus.valueOf(rs.getString("order_status")), (LocalDateTime) rs.getObject("created_at")));
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
    public int getTotalPriceByOrderId(Long order_id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int total_price = 0;
        try {
            conn = DBUtil.getConnection();
            String SQL = "select sum(price) as total_price from (products p join order_items i on p.product_id = i.product_id) join orders o on i.order_id = o.order_id  group by o.order_id having o.order_id = ?;";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setLong(1, order_id);
            rs = pstmt.executeQuery();
            while (rs.next()){
                total_price = rs.getInt("total_price");
            }
            return total_price;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(rs, pstmt, conn);
        }
    }

    @Override
    public List<Order> findByUserId(Long user_id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String SQL;
        List<Order> orders = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            SQL = "select * from orders where user_id = " + user_id;
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
            while (rs.next()){
                orders.add(new Order(rs.getLong("order_id"), rs.getLong("user_id"), rs.getString("order_detail"), rs.getInt("total_price"), OrderStatus.valueOf(rs.getString("order_status")), (LocalDateTime) rs.getObject("created_at")));
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(rs, pstmt, conn);
        }
    }
}
