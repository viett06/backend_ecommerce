package com.devteria.identity_service.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequest {
    //private Long productId;
    private Long quantity;
}
