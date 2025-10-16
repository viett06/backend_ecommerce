package com.devteria.identity_service.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {
    private String street;
    private String city;
    private String postalCode;
    private String country;
    private boolean defaultAddress;
}
