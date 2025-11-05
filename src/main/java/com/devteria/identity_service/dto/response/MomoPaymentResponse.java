package com.devteria.identity_service.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MomoPaymentResponse {
    private String requestId;
    private String orderId;
    private BigDecimal amount;
    private String orderInfo;
    private String payUrl;
    private String qrCodeUrl;
    private String deeplink;
    private String signature;
}