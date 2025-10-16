package com.devteria.identity_service.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InventoryProfileRequest {
    private Integer quantity;
}
