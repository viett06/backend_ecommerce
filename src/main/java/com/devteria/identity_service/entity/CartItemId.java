package com.devteria.identity_service.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItemId implements Serializable {
    private Long productId;
    private Long cartId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItemId that)) return false;
        return Objects.equals(productId, that.productId) &&
                Objects.equals(cartId, that.cartId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, cartId);
    }
}
