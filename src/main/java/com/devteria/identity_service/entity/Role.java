package com.devteria.identity_service.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role {
    @Id
    private String name;
    private String description;
    @ManyToMany
    private Set<Permission> permissions = new HashSet<>();

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
