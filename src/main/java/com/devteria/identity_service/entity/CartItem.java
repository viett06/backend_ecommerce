package com.devteria.identity_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(
        name = "cartItem"
)
public class CartItem {
    @EmbeddedId
    private CartItemId cartItemId;
    @Column(nullable = false)
    @Min(1)
    private Long quantity;
    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
    @ManyToOne
    @MapsId(
            "cartId"
    )
    @JoinColumn(
            name = "cart_id",
            referencedColumnName = "cartId",
            nullable = false

    )
    private Cart cart;
    @ManyToOne
    @MapsId(
            "productId"
    )
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "productId"
    )
    private Product product;

}
