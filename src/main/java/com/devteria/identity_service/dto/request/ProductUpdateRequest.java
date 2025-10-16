package com.devteria.identity_service.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductUpdateRequest {
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String imageUrl;
}
