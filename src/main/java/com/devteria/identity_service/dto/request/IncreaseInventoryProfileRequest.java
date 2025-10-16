package com.devteria.identity_service.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class IncreaseInventoryProfileRequest {
    private Integer quantityToAdd;
}
