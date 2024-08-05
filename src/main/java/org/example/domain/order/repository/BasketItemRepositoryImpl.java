package org.example.domain.order.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.example.domain.order.BasketItemEntity;
import org.example.domain.order.service.BasketItemRepository;
import org.example.util.DBUtil;

public class BasketItemRepositoryImpl implements BasketItemRepository {

    private Connection conn;
    private PreparedStatement psmt;
    private String query;

    @Override
    public void save(BasketItemEntity basketItemEntity) {
        try {
            conn = DBUtil.getConnection();
            query = "INSERT INTO basket_items (user_id, product_id, quantity) VALUES (?, ?, ?)";
            psmt = conn.prepareStatement(query);
            psmt.setLong(1, basketItemEntity.userId());
            psmt.setLong(2, basketItemEntity.productId());
            psmt.setInt(3, basketItemEntity.quantity());
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, psmt);
        }
    }

    @Override
    public List<BasketItemEntity> findByUserId(Long userId) {

        List<BasketItemEntity> basketItemEntities = new ArrayList<>();

        try {
            conn = DBUtil.getConnection();
            query = "SELECT * FROM basket_items WHERE user_id = ?";
            psmt = conn.prepareStatement(query);
            psmt.setLong(1, userId);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                BasketItemEntity basketItemEntity = BasketItemEntity.of(rs.getLong("user_id"),
                        rs.getLong("product_id"), rs.getInt("quantity"));
                basketItemEntities.add(basketItemEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, psmt);
        }
        return basketItemEntities;
    }

    @Override
    public void increaseQuantity(Long userId, Long productId) {
        try {
            conn = DBUtil.getConnection();
            query = "UPDATE basket_items SET quantity = quantity + 1 WHERE (user_id, product_id) = (?, ?)";
            psmt = conn.prepareStatement(query);
            psmt.setLong(1, userId);
            psmt.setLong(2, productId);
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, psmt);
        }
    }

    @Override
    public void decreaseQuantity(Long userId, Long productId) {
        try {
            conn = DBUtil.getConnection();
            query = "UPDATE basket_items SET quantity = quantity - 1 WHERE (user_id, product_id) = (?, ?)";
            psmt = conn.prepareStatement(query);
            psmt.setLong(1, userId);
            psmt.setLong(2, productId);
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, psmt);
        }
    }

    @Override
    public void delete(Long userId, Long productId) {
        try {
            conn = DBUtil.getConnection();
            query = "DELETE FROM basket_items WHERE (user_id, product_id) = (?, ?)";
            psmt = conn.prepareStatement(query);
            psmt.setLong(1, userId);
            psmt.setLong(2, productId);
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, psmt);
        }
    }
}
