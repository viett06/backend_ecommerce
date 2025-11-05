package com.devteria.identity_service.dto.request;

import jakarta.validation.constraints.NotBlank;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MomoPaymentRequest {

    @NotBlank(message = "Order info is required")
    private String orderInfo;

    @NotBlank(message = "Return URL is required")
    private String returnUrl;

    @NotBlank(message = "Notify URL is required")
    private String notifyUrl;

    private String extraData;
}