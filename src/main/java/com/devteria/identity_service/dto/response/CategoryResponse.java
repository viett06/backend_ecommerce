package com.devteria.identity_service.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryResponse {
    private Long categoryId;
    private String categoryName;
    private String description;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Set<ProductResponse> products;
}
