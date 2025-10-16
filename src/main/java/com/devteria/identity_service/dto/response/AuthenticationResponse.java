package com.devteria.identity_service.dto.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticationResponse {
    private boolean authenticated;
    private String token;
}
