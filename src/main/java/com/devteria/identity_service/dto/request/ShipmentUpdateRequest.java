package com.devteria.identity_service.dto.request;

import com.devteria.identity_service.enums.ShipmentStatus;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipmentUpdateRequest {

    private LocalDate shippedDate;

    @Size(max = 100, message = "Carrier must not exceed 100 characters")
    private String carrier;

    @Size(max = 50, message = "Tracking number must not exceed 50 characters")
    private String trackingNumber;

    private ShipmentStatus status;
}