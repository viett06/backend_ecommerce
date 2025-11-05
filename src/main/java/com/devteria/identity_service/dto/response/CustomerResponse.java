package com.devteria.identity_service.dto.response;

import com.devteria.identity_service.entity.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerResponse {
    private String customerId;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private boolean gender;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private UserResponse userResponse;
    private Set<AddressResponse> addresses;
//    private Set<Review> reviews;
    private CartResponse cartResponse;
    private Set<OrderResponse> orderResponses;

}
