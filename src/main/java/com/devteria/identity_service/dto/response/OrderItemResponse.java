package com.devteria.identity_service.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemResponse {
    private Long orderId;
    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal priceEach;
    private BigDecimal subTotal;
    private Boolean reviewed;
}