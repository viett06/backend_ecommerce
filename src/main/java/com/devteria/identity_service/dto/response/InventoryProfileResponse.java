package com.devteria.identity_service.dto.response;


import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InventoryProfileResponse {
    private Long inventoryId;
    private Integer quantity;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

}
