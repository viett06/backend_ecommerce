package com.devteria.identity_service.dto.response;

import com.devteria.identity_service.enums.PaymentMethod;
import com.devteria.identity_service.enums.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {
    private Long paymentId;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private PaymentMethod paymentMethod;
    private PaymentStatus status;
    private String transactionId;
    private String orderInfo;
    private String payUrl;
    private String qrCodeUrl;
    private List<Long> orderIds;
}