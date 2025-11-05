package com.devteria.identity_service.dto.request;

import com.devteria.identity_service.enums.PaymentStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentUpdateRequest {
    private PaymentStatus status;
}