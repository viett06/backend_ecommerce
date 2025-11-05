package com.devteria.identity_service.dto.response;

import com.devteria.identity_service.enums.ShipmentStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipmentResponse {
    private Long shipmentId;
    private LocalDate shippedDate;
    private String carrier;
    private String trackingNumber;
    private ShipmentStatus status;
    private List<Long> orderIds;
    private Integer orderCount;
}