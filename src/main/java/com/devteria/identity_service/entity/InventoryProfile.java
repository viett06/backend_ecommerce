package com.devteria.identity_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "inventory_profile"
)
public class InventoryProfile {
    @Id
//    @SequenceGenerator(
//            name = "inventory_profile_sequence",
//            sequenceName = "inventory_profile_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "inventory_profile_sequence"
//    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;
    private Integer quantity;
    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
    @OneToOne(
            optional = false

    )
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "productId"
    )
    private Product product;

}
