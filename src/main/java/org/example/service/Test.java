package org.example.service;

import java.math.BigDecimal;
import org.example.jdbcTest.Product;

public class Test {

  public static void main(String[] args) {
    ProductRepositoryImpl productRepository = new ProductRepositoryImpl();

    productRepository.save(Product.of("컴퓨터", BigDecimal.valueOf(10000), 10));

    productRepository.findByName("컴퓨터")
        .forEach(System.out::println);
  }

}
