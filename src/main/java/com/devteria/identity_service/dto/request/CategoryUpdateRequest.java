package com.devteria.identity_service.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryUpdateRequest {
    private String categoryName;
    private String description;
}
