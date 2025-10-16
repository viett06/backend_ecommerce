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
    @SequenceGenerator(
            name = "address_sequence",
            sequenceName = "address_sequence",
            allocationSize = 1
            )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "address_sequence"

    )
    private Long addressId;
    private String street;
    private String city;
    private String postalCode;
    private String country;
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
