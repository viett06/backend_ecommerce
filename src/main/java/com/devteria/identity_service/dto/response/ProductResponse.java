package com.devteria.identity_service.dto.response;

import com.devteria.identity_service.entity.InventoryProfile;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long productId;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String imageUrl;
    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
    private InventoryProfileResponse inventoryProfileResponse;
    //private Set<CartItemResponse> cartItemResponses;
}
