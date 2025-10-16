package com.devteria.identity_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(
        name = "product"
)
public class Product {
    @Id
//    @SequenceGenerator(
//            name = "product_sequence",
//            sequenceName = "product_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "product_sequence"
//    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String imageUrl;
    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
//    @OneToMany(
//            mappedBy = "product",
//            orphanRemoval = true
//    )
//    private Set<Review> reviews = new HashSet<>();
    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<CartItem> cartItems = new HashSet<>();
    @ManyToOne(optional = false)
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "categoryId"
    )
    private Category category;
    @OneToOne(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private InventoryProfile inventoryProfile;
//    @OneToMany(
//            mappedBy = "product",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private Set<PromotionProduct> promotionProducts = new HashSet<>();
//    @OneToMany(
//            mappedBy = "product"
//    )
//    private Set<OrderItem> orderItems = new HashSet<>();

}
