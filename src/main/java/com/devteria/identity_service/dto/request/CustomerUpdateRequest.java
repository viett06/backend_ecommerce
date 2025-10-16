package com.devteria.identity_service.dto.request;

import com.devteria.identity_service.validator.DobConstraint;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CustomerUpdateRequest {
    private String firstName;
    private String lastName;
    @DobConstraint(min=3,message = "INVALID_DOB")
    private LocalDate dob;
    private boolean gender;
}
