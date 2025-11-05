package com.devteria.identity_service.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    @Size(max = 500, message = "Note must not exceed 500 characters")
    private String note;
}
