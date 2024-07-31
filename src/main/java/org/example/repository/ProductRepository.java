package org.example.repository;

import java.math.BigDecimal;
import java.util.List;
import org.example.jdbcTest.Product;

public interface ProductRepository {

  void save(Product product);

  List<Product> findByPrice(BigDecimal price);

  void deleteById(Long productId);

  void update(Product product);

  List<Product> findByName(String productName);

  List<Product> findAll();

  Product findById(Long productId);
}
