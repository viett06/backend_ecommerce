package com.devteria.identity_service.dto.response;

import lombok.*;

import java.time.LocalDateTime;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponse {
    private Long cartId;
    private Long productId;
    private Long quantity;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
