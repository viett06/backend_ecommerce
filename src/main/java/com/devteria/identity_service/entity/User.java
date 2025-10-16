package com.devteria.identity_service.entity;


import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;
    @Column(name="name",unique = true,columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    private String name;
    private String passWord;
    @ManyToMany
    private Set<Role> roles = new HashSet<>();
    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Customer customer;
}

