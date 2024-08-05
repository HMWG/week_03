package org.example.domain.order.repository;

import org.example.domain.order.OrderEntity;
import org.example.domain.order.OrderStatus;
import org.example.domain.order.service.OrderRepository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.example.util.DBUtil;

public class OrderRepositoryImpl implements OrderRepository {

    public OrderRepositoryImpl() {
    }

    @Override
    public long save(OrderEntity orderEntity) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String SQL;
        String SQL2;
        long orderId = 0;

        try {
            conn = DBUtil.getConnection();
            SQL = "insert into orders(order_detail, user_id, total_price, order_status) values(?,?,?,?)";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, orderEntity.getOrderDetail());
            pstmt.setLong(2, orderEntity.getUserId());
            pstmt.setInt(3, orderEntity.getTotalPrice());
            pstmt.setString(4, String.valueOf(orderEntity.getOrderStatus()));
            pstmt.executeUpdate();
            SQL2 = "select order_id from orders order by order_id desc limit 1";
            pstmt = conn.prepareStatement(SQL2);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                orderId = rs.getLong("order_id");
            }
            return orderId;
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
    public int update(OrderEntity orderEntity) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String SQL;

        try {
            conn = DBUtil.getConnection();
            SQL = "UPDATE orders set";
            if (orderEntity.getOrderDetail() != null && !orderEntity.getOrderDetail().isEmpty()) {
                SQL += " order_detail = '" + orderEntity.getOrderDetail() + "', ";
            }
            if (orderEntity.getOrderDetail() != null && !orderEntity.getOrderDetail().isEmpty()) {
                SQL += " order_detail = '" + orderEntity.getOrderDetail() + "', ";
            }
            if (orderEntity.getOrderStatus() != null && !orderEntity.getOrderStatus().toString().isEmpty()) {
                SQL += " order_status = '" + orderEntity.getOrderStatus() + "', ";
            }
            SQL += " total_price = ? ";
            SQL += "where order_id = " + orderEntity.getOrderId();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1,getTotalPriceByOrderId(orderEntity.getOrderId()));
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(pstmt, conn);
        }
    }

    @Override
    public OrderEntity findByOrderId(Long order_id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String SQL;

        OrderEntity orderEntity = null;

        try {
            conn = DBUtil.getConnection();
            SQL = "select * from orders where order_id = ?";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setLong(1, order_id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                orderEntity = new OrderEntity(rs.getLong("order_id"), rs.getLong("user_id"), rs.getString("order_detail"), rs.getInt("total_price"), OrderStatus.valueOf(rs.getString("order_status")), (LocalDateTime) rs.getObject("created_at"));
            }
            return orderEntity;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(rs, pstmt, conn);
        }
    }

    @Override
    public List<OrderEntity> findAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String SQL;
        List<OrderEntity> orderEntities = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            SQL = "select * from orders";
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
            while (rs.next()){
                orderEntities.add(new OrderEntity(rs.getLong("order_id"), rs.getLong("user_id"), rs.getString("order_detail"), rs.getInt("total_price"), OrderStatus.valueOf(rs.getString("order_status")), (LocalDateTime) rs.getObject("created_at")));
            }
            return orderEntities;
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
    public List<OrderEntity> findByUserId(Long user_id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String SQL;
        List<OrderEntity> orderEntities = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            SQL = "select * from orders where user_id = " + user_id;
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
            while (rs.next()){
                orderEntities.add(new OrderEntity(rs.getLong("order_id"), rs.getLong("user_id"), rs.getString("order_detail"), rs.getInt("total_price"), OrderStatus.valueOf(rs.getString("order_status")), (LocalDateTime) rs.getObject("created_at")));
            }
            return orderEntities;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(rs, pstmt, conn);
        }
    }
}
