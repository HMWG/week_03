package org.example.domain.product.repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.example.domain.product.Product;
import org.example.domain.product.service.ProductRepository;
import org.example.util.DBUtil;

public class ProductRepositoryImpl implements ProductRepository {

  private Connection conn;
  private PreparedStatement psmt;
  private String query;

  @Override
  public void save(Product product) {
    try {
      conn = DBUtil.getConnection();
      query = "INSERT INTO products (name, price, quantity) VALUES (?, ?, ?)";
      psmt = conn.prepareStatement(query);
      psmt.setString(1, product.productName());
      psmt.setBigDecimal(2, product.price());
      psmt.setInt(3, product.quantity());
      psmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DBUtil.close(psmt, conn);
    }
  }

  @Override
  public List<Product> findByPrice(BigDecimal price) {
    List<Product> products = new ArrayList<>();

    try {
      conn = DBUtil.getConnection();
      query = "SELECT * FROM products WHERE price < ?";
      psmt = conn.prepareStatement(query);
      psmt.setBigDecimal(1, price);
      ResultSet rs = psmt.executeQuery();

      while (rs.next()) {
        products.add(Product.of(
            rs.getLong("product_id"),
            rs.getString("name"),
            rs.getBigDecimal("price"),
            rs.getInt("quantity"),
            (LocalDateTime) rs.getObject("created_at")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DBUtil.close(psmt, conn);
    }
    return products;
  }

  @Override
  public void deleteById(Long productId) {
    try {
      conn = DBUtil.getConnection();
      query = "DELETE FROM products WHERE product_id = ?";
      psmt = conn.prepareStatement(query);
      psmt.setLong(1, productId);
      psmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DBUtil.close(psmt, conn);
    }
  }

  @Override
  public void update(Product product) {
    try {
      conn = DBUtil.getConnection();
      query = "UPDATE products SET name = ?, price = ?, quantity = ? WHERE product_id = ?";
      psmt = conn.prepareStatement(query);
      psmt.setString(1, product.productName());
      psmt.setBigDecimal(2, product.price());
      psmt.setInt(3, product.quantity());
      psmt.setLong(4, product.productId());
      psmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DBUtil.close(psmt, conn);
    }
  }

  @Override
  public List<Product> findByName(String productName) {
    List<Product> products = new ArrayList<>();

    try {
      conn = DBUtil.getConnection();
      query = "SELECT * FROM products WHERE name = ?";
      psmt = conn.prepareStatement(query);
      psmt.setString(1, productName);
      ResultSet rs = psmt.executeQuery();

      while (rs.next()) {
        products.add(Product.of(
            rs.getLong("product_id"),
            rs.getString("name"),
            rs.getBigDecimal("price"),
            rs.getInt("quantity"),
            (LocalDateTime) rs.getObject("created_at")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DBUtil.close(psmt, conn);
    }
    return products;
  }

  @Override
  public List<Product> findAll() {
    List<Product> products = new ArrayList<>();

    try {
      conn = DBUtil.getConnection();
      query = "SELECT * FROM products";
      psmt = conn.prepareStatement(query);
      ResultSet rs = psmt.executeQuery();

      while (rs.next()) {
        products.add(Product.of(
            rs.getLong("product_id"),
            rs.getString("name"),
            rs.getBigDecimal("price"),
            rs.getInt("quantity"),
            (LocalDateTime) rs.getObject("created_at")));

      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DBUtil.close(psmt, conn);
    }
    return products;
  }

  @Override
  public Product findById(Long productId) {
    Product product = null;

    try {
      conn = DBUtil.getConnection();
      query = "SELECT * FROM products WHERE product_id = ?";
      psmt = conn.prepareStatement(query);
      psmt.setLong(1, productId);
      ResultSet rs = psmt.executeQuery();

      if (rs.next()) {
        product = Product.of(
            rs.getLong("product_id"),
            rs.getString("name"),
            rs.getBigDecimal("price"),
            rs.getInt("quantity"),
            (LocalDateTime) rs.getObject("created_at"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DBUtil.close(psmt, conn);
    }
    return product;
  }

  @Override
  public void decreaseQuantity(Product product, Integer quantity) {


    try {
      conn = DBUtil.getConnection();
      query = "UPDATE products SET quantity = quantity - ? WHERE product_id = ?";
      psmt = conn.prepareStatement(query);
      psmt.setInt(1, quantity);
      psmt.setLong(2, product.productId());
      psmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DBUtil.close(psmt, conn);
    }
  }

}
