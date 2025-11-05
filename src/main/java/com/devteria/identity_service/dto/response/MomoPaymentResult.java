package com.devteria.identity_service.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MomoPaymentResult {
    private String partnerCode;
    private String orderId;
    private String requestId;
    private BigDecimal amount;
    private Long responseTime;
    private String message;
    private Integer resultCode;
    private String payUrl;
    private String qrCodeUrl;
    private String deeplink;
    private String signature;
}