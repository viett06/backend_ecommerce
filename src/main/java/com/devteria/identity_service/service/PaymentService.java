package com.devteria.identity_service.service;

import com.devteria.identity_service.dto.request.PaymentRequest;
import com.devteria.identity_service.dto.response.PaymentResponse;
import com.devteria.identity_service.enums.PaymentMethod;
import com.devteria.identity_service.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface PaymentService {
    PaymentResponse createPayment(PaymentRequest request);
    PaymentResponse createMomoPayment(PaymentRequest request);
    PaymentResponse processMomoCallback(String partnerCode, String orderId, String requestId,
                                        String amount, String orderInfo, String orderType,
                                        String transId, String payType, String signature);
    List<PaymentResponse> getAllPayments();
    PaymentResponse getPaymentById(Long paymentId);
    PaymentResponse getPaymentByTransactionId(String transactionId);
    List<PaymentResponse> getPaymentsByMethod(PaymentMethod method);
    List<PaymentResponse> getPaymentsByStatus(PaymentStatus status);
    List<PaymentResponse> getPaymentsByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    BigDecimal getTotalRevenue(LocalDateTime startDate, LocalDateTime endDate);
    PaymentResponse updatePaymentStatus(Long paymentId, PaymentStatus status);
    void deletePayment(Long paymentId);
}