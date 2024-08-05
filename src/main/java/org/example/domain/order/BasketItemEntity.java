package org.example.domain.order;

public record BasketItemEntity(
        Long userId,
        Long productId,
        Integer quantity
) {
    public static BasketItemEntity of(Long userId, Long productId, Integer quantity) {
        return new BasketItemEntity(
                userId,
                productId,
                quantity
        );
    }
}
