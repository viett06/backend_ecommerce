package com.devteria.identity_service.controller;

import com.devteria.identity_service.dto.request.PaymentRequest;
import com.devteria.identity_service.dto.response.ApiResponse;
import com.devteria.identity_service.dto.response.PaymentResponse;
import com.devteria.identity_service.enums.PaymentMethod;
import com.devteria.identity_service.enums.PaymentStatus;
import com.devteria.identity_service.service.PaymentService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentController {

    PaymentService paymentService;

    @PostMapping
    public ApiResponse<PaymentResponse> createPayment(@Valid @RequestBody PaymentRequest request) {
        PaymentResponse response = paymentService.createPayment(request);
        return ApiResponse.<PaymentResponse>builder()
                .result(response)
                .build();
    }

    @PostMapping("/momo/callback")
    public ApiResponse<PaymentResponse> momoCallback(@RequestBody Map<String, String> callbackData) {
        log.info("Received MoMo callback: {}", callbackData);

        PaymentResponse response = paymentService.processMomoCallback(
                callbackData.get("partnerCode"),
                callbackData.get("orderId"),
                callbackData.get("requestId"),
                callbackData.get("amount"),
                callbackData.get("orderInfo"),
                callbackData.get("orderType"),
                callbackData.get("transId"),
                callbackData.get("payType"),
                callbackData.get("signature")
        );

        return ApiResponse.<PaymentResponse>builder()
                .result(response)
                .build();
    }

    @GetMapping("/momo/return")
    public ApiResponse<String> momoReturn(@RequestParam Map<String, String> params) {
        log.info("MoMo return URL called with params: {}", params);
        // Handle user return from MoMo payment page
        return ApiResponse.<String>builder()
                .result("Payment processing completed. You can close this window.")
                .build();
    }

    @GetMapping
    public ApiResponse<List<PaymentResponse>> getAllPayments() {
        List<PaymentResponse> responses = paymentService.getAllPayments();
        return ApiResponse.<List<PaymentResponse>>builder()
                .result(responses)
                .build();
    }

    @GetMapping("/{paymentId}")
    public ApiResponse<PaymentResponse> getPaymentById(@PathVariable Long paymentId) {
        PaymentResponse response = paymentService.getPaymentById(paymentId);
        return ApiResponse.<PaymentResponse>builder()
                .result(response)
                .build();
    }

    @GetMapping("/transaction/{transactionId}")
    public ApiResponse<PaymentResponse> getPaymentByTransactionId(@PathVariable String transactionId) {
        PaymentResponse response = paymentService.getPaymentByTransactionId(transactionId);
        return ApiResponse.<PaymentResponse>builder()
                .result(response)
                .build();
    }

    @GetMapping("/method/{method}")
    public ApiResponse<List<PaymentResponse>> getPaymentsByMethod(@PathVariable PaymentMethod method) {
        List<PaymentResponse> responses = paymentService.getPaymentsByMethod(method);
        return ApiResponse.<List<PaymentResponse>>builder()
                .result(responses)
                .build();
    }

    @GetMapping("/status/{status}")
    public ApiResponse<List<PaymentResponse>> getPaymentsByStatus(@PathVariable PaymentStatus status) {
        List<PaymentResponse> responses = paymentService.getPaymentsByStatus(status);
        return ApiResponse.<List<PaymentResponse>>builder()
                .result(responses)
                .build();
    }

    @GetMapping("/revenue")
    public ApiResponse<BigDecimal> getTotalRevenue(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        BigDecimal revenue = paymentService.getTotalRevenue(startDate, endDate);
        return ApiResponse.<BigDecimal>builder()
                .result(revenue)
                .build();
    }

    @PutMapping("/{paymentId}/status")
    public ApiResponse<PaymentResponse> updatePaymentStatus(
            @PathVariable Long paymentId,
            @RequestParam PaymentStatus status) {
        PaymentResponse response = paymentService.updatePaymentStatus(paymentId, status);
        return ApiResponse.<PaymentResponse>builder()
                .result(response)
                .build();
    }

    @DeleteMapping("/{paymentId}")
    public ApiResponse<Void> deletePayment(@PathVariable Long paymentId) {
        paymentService.deletePayment(paymentId);
        return ApiResponse.<Void>builder()
                .build();
    }
}