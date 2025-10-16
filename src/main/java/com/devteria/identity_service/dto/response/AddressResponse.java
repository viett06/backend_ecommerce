package com.devteria.identity_service.dto.response;

import lombok.*;

import java.time.LocalDateTime;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {
    private Long addressId;
    private String street;
    private String city;
    private String postalCode;
    private String country;
    private boolean defaultAddress;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
