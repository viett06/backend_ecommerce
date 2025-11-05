package com.devteria.identity_service.entity;

import jakarta.persistence.*;
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
        name = "address"
)
public class Address {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    @Column(nullable = false, length = 255)
    private String street;
    @Column(nullable = false, length = 100)
    private String city;
    @Column(name = "postal_code", nullable = false, length = 20)
    private String postalCode;
    @Column(nullable = false, length = 100)
    private String country;
    @Column(name = "is_default", nullable = false)
    private boolean defaultAddress;
    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
    @ManyToOne
    @JoinColumn(
            name = "customer_id",
            referencedColumnName = "customerId"
    )
    private Customer customer;
}
