package org.example.domain.order;

import org.example.domain.product.Product;

public record BasketItem(
        Product product,
        int quantity
) {
    public static BasketItem of(Product product, int quantity) {
        return new BasketItem(product, quantity);
    }
}
