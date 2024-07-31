package org.example.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.example.jdbcTest.Product;
import org.example.repository.ProductRepository;

public class ProductRepositoryImpl implements ProductRepository {

  private Connection conn;
  private PreparedStatement psmt;
  private String query;

  @Override
  public void save(Product product) {
    conn = connectDb();
    query = "INSERT INTO products (name, price, quantity) VALUES (?, ?, ?)";

    try {
      psmt = conn.prepareStatement(query);
      psmt.setString(1, product.productName());
      psmt.setBigDecimal(2, product.price());
      psmt.setInt(3, product.quantity());
      psmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (conn != null || psmt != null) {
          psmt.close();
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public List<Product> findByPrice(BigDecimal price) {
    conn = connectDb();
    query = "SELECT * FROM products WHERE price < ?";

    List<Product> products = new ArrayList<>();

    try {
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
      try {
        if (conn != null || psmt != null) {
          psmt.close();
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return products;
  }

  @Override
  public void deleteById(Long productId) {
    conn = connectDb();
    query = "DELETE FROM products WHERE product_id = ?";

    try {
      psmt = conn.prepareStatement(query);
      psmt.setLong(1, productId);
      psmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (conn != null || psmt != null) {
          psmt.close();
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void update(Product product) {
    conn = connectDb();
    query = "UPDATE products SET name = ?, price = ?, quantity = ? WHERE product_id = ?";

    try {
      psmt = conn.prepareStatement(query);
      psmt.setString(1, product.productName());
      psmt.setBigDecimal(2, product.price());
      psmt.setInt(3, product.quantity());
      psmt.setLong(4, product.productId());
      psmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (conn != null || psmt != null) {
          psmt.close();
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public List<Product> findByName(String productName) {
    conn = connectDb();
    query = "SELECT * FROM products WHERE name = ?";

    List<Product> products = new ArrayList<>();

    try {
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
      try {
        if (conn != null || psmt != null) {
          psmt.close();
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return products;
  }

  @Override
  public List<Product> findAll() {
    conn = connectDb();
    query = "SELECT * FROM products";

    List<Product> products = new ArrayList<>();

    try {
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
      try {
        if (conn != null || psmt != null) {
          psmt.close();
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return products;
  }

  @Override
  public Product findById(Long productId) {
    conn = connectDb();
    query = "SELECT * FROM products WHERE product_id = ?";

    Product product = null;

    try {
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
      try {
        if (conn != null || psmt != null) {
          psmt.close();
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return product;
  }

  private Connection connectDb() {
    try {
      return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + System.getenv("DB_SCHEMA"), System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}
