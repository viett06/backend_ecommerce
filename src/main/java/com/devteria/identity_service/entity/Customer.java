package com.devteria.identity_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(
        name = "customer"

)

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String customerId;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private boolean gender;
    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
    @OneToOne(
            optional = false
    )
    @JoinColumn(
            name = "user_id", // cột FK trong bảng customer
            referencedColumnName = "userId",  // cột PK trong bảng user
            nullable = false    // FK không được null

    )
    private User user;

    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Address> addresses = new HashSet<>();
//    @OneToMany(
//            mappedBy = "customer",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY
//
//    )
//    private Set<Review> reviews = new HashSet<>();
    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "customer"
    )
    private Cart cart;
//    @OneToMany(
//            mappedBy = "customer"
//    )
//    private Set<Order> orders = new HashSet<>();
//
}

