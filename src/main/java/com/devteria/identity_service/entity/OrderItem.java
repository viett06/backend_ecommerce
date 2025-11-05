package com.devteria.identity_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(
        name = "order_item"
)
public class OrderItem {
    @EmbeddedId
    private OrderItemId orderItemId;
    @Column(nullable = false)
    @Min(1)
    private int quantity;
    @Column(name = "price_each", nullable = false, precision = 10, scale = 2)
    private BigDecimal priceEach;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @MapsId(
            "orderId"
    )
    @JoinColumn(
            name = "order_id",
            referencedColumnName = "orderId"
    )
    private Order order;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @MapsId(
            "productId"
    )
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "productId"
    )
    private Product product;
    @OneToOne(
            mappedBy = "orderItem",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Review review;
}
