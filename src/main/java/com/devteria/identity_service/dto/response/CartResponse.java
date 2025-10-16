package com.devteria.identity_service.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {
    private Long cartId;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Set<CartItemResponse> cartItemResponse;
}
