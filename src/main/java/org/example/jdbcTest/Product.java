package org.example.jdbcTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Product(
    Long productId,
    String productName,
    BigDecimal price,
    Integer quantity,
    LocalDateTime createdAt
) {

  public static Product of(Long productId, String productName, BigDecimal price, Integer quantity, LocalDateTime createdAt) {
    return new Product(
        productId,
        productName,
        price,
        quantity,
        createdAt
    );
  }

  public static Product of(String productName, BigDecimal price, Integer quantity) {
    return new Product(
        null,
        productName,
        price,
        quantity,
        null
    );
  }
}
