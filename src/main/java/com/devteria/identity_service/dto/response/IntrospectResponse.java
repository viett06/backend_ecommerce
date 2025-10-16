package com.devteria.identity_service.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IntrospectResponse {
    private boolean valid;
}
