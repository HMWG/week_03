package org.example.domain;

public record BasketItem(
        Long userId,
        Long productId,
        Integer quantity
) {
    public static BasketItem of(Long userId, Long productId, Integer quantity) {
        return new BasketItem(
                userId,
                productId,
                quantity
        );
    }
}
