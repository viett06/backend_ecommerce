package com.devteria.identity_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(
        name = "cart"
)
public class Cart {
    @Id
//    @SequenceGenerator(
//            name = "cart_sequence",
//            sequenceName = "cart_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "cart_sequence"
//    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;
    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
    @OneToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "customer_id",
            referencedColumnName = "customerId",
            nullable = false,
            unique = true
    )
    private Customer customer;
    @OneToMany(
            mappedBy = "cart",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<CartItem> cartItems = new HashSet<>();
}
