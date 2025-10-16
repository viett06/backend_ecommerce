package com.devteria.identity_service.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressUpdateRequest {
    private String street;
    private String city;
    private String postalCode;
    private String country;
    private boolean defaultAddress;
}
