package com.devteria.identity_service.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String imageUrl;
}
