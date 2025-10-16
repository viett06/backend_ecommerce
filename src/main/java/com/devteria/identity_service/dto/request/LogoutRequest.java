package com.devteria.identity_service.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogoutRequest {
    private String token;
}
