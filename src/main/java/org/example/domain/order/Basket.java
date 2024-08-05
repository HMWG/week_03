package org.example.domain.order;

import java.math.BigDecimal;
import java.util.List;

public record Basket(List<BasketItem> basketItems, int totalPrice) {
    public static Basket from(List<BasketItem> basketItems) {
        return new Basket(basketItems,
                basketItems.stream()
                        .mapToInt(basketItem -> basketItem.product().price().multiply(BigDecimal.valueOf(basketItem.quantity())).intValue())
                        .sum()
                );
    }
}
