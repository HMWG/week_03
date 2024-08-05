package org.example.repositoryImpl;

import org.example.domain.Order;
import org.example.repository.OrderRepository;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    final String url = "jdbc:mysql://127.0.0.1:3306/shop";
    final String user = "root";
    final String password = "1234";



    public OrderRepositoryImpl() {
    }

    @Override
    public int save(int user_id, String order_detail, String order_status) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String SQL;

        try {
            conn = DriverManager.getConnection(url, user, password);
            SQL = "insert into orders(order_detail, user_id, total_price, order_status) values(?,?,?,?)";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, order_detail);
            pstmt.setInt(2, user_id);
            pstmt.setInt(3, getTotalPriceByOrderId(user_id));
            pstmt.setString(4, order_status);
            int result = pstmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            System.out.println(1);
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            // 6. 사용 완료한 리소스 반납 ( 생성한 순서의 역순으로 )
            if(pstmt!=null){
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public int delete(int order_id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String SQL;

        try {
            conn = DriverManager.getConnection(url, user, password);
            SQL = "DELETE FROM orders where order_id = ?";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, order_id);
            int result = pstmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            // 6. 사용 완료한 리소스 반납 ( 생성한 순서의 역순으로 )
            if(pstmt!=null){
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public int update(int order_id,int user_id, String order_detail, String order_status) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String SQL;

        try {
            conn = DriverManager.getConnection(url, user, password);
            SQL = "UPDATE orders set user_id = ?, order_detail = ?, order_status = ? Where order_id = ?";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, user_id);
            pstmt.setString(2, order_detail);
            pstmt.setString(3, order_status);
            pstmt.setInt(4, order_id);
            int result = pstmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            // 6. 사용 완료한 리소스 반납 ( 생성한 순서의 역순으로 )
            if(pstmt!=null){
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
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
            conn = DriverManager.getConnection(url, user, password);
            SQL = "select * from orders where order_id = ?";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, order_id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                order = new Order(rs.getLong("order_id"), rs.getLong("user_id"), rs.getString("order_detail"), rs.getInt("total_price"), rs.getString("order_status"), rs.getDate("created_at").toInstant().atZone(ZoneId.systemDefault()) .toLocalDateTime());
            }
            return order;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            // 6. 사용 완료한 리소스 반납 ( 생성한 순서의 역순으로 )
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(pstmt!=null){
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
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
            conn = DriverManager.getConnection(url, user, password);
            SQL = "select * from orders";
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
            while (rs.next()){
                orders.add(new Order(rs.getLong("order_id"), rs.getLong("user_id"), rs.getString("order_detail"), rs.getInt("total_price"), rs.getString("order_status"), rs.getDate("created_at").toInstant().atZone(ZoneId.systemDefault()) .toLocalDateTime()));
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            // 6. 사용 완료한 리소스 반납 ( 생성한 순서의 역순으로 )
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(pstmt!=null){
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    @Override
    public int getTotalPriceByOrderId(int order_id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int total_price = 0;
        try {
            conn = DriverManager.getConnection(url, user, password);
            String SQL = "select sum(price) as total_price from (products p join order_items i on p.product_id = i.product_id) join orders o on i.order_id = o.order_id  group by o.order_id having o.order_id = ?;";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, order_id);
            rs = pstmt.executeQuery();
            while (rs.next()){
                total_price = rs.getInt("total_price");
            }
            return total_price;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            // 6. 사용 완료한 리소스 반납 ( 생성한 순서의 역순으로 )
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(pstmt!=null){
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
