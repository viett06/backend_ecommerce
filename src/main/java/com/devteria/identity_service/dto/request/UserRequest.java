package com.devteria.identity_service.dto.request;

import com.devteria.identity_service.validator.DobConstraint;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @Column(unique = true)
    private String name;
    private String passWord;

//    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    @DobConstraint(min=3,message = "INVALID_DOB")
//    private LocalDate dob;
}
