package com.devteria.identity_service.dto.request;

import com.devteria.identity_service.enums.ShipmentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentRequest {

    private LocalDate shippedDate;

    @NotBlank(message = "Carrier is required")
    @Size(max = 100, message = "Carrier must not exceed 100 characters")
    private String carrier;

    @NotBlank(message = "Tracking number is required")
    @Size(max = 50, message = "Tracking number must not exceed 50 characters")
    private String trackingNumber;

    @NotNull(message = "Status is required")
    private ShipmentStatus status;
}
