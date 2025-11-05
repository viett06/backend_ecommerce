package com.devteria.identity_service.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private Long orderId;
    private LocalDateTime orderDate;
    private String status;
    private String note;
    private Long customerId;
    private Set<OrderItemResponse> orderItemResponses;
    private ShipmentResponse shipmentResponse;
    private PaymentResponse paymentResponse;
}
