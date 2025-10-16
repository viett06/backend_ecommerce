package com.devteria.identity_service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum Role {
    ADMIN("ADMIN","Administrator role with full access"),
    USER("USER","Basic user role with limited access");
    private String name;
    private String description;

}
