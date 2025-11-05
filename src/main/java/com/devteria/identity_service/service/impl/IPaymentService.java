package com.devteria.identity_service.service.impl;

import com.devteria.identity_service.configuration.MomoConfig;
import com.devteria.identity_service.dto.request.PaymentRequest;
import com.devteria.identity_service.dto.response.MomoPaymentResult;
import com.devteria.identity_service.dto.response.PaymentResponse;
import com.devteria.identity_service.entity.Order;
import com.devteria.identity_service.entity.Payment;
import com.devteria.identity_service.enums.PaymentMethod;
import com.devteria.identity_service.enums.PaymentStatus;
import com.devteria.identity_service.exception.AppException;
import com.devteria.identity_service.exception.ErrorCode;
import com.devteria.identity_service.mapper.PaymentMapper;
import com.devteria.identity_service.repository.OrderRepository;
import com.devteria.identity_service.repository.PaymentRepository;
import com.devteria.identity_service.service.PaymentService;
import com.devteria.identity_service.utils.PaymentUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IPaymentService implements PaymentService {

    PaymentRepository paymentRepository;
    OrderRepository orderRepository;
    PaymentMapper paymentMapper;
    MomoConfig momoConfig;
    RestTemplate restTemplate;

    @Override
    @Transactional
    public PaymentResponse createPayment(PaymentRequest request) {
        if (request.getPaymentMethod() == PaymentMethod.MOMO) {
            return createMomoPayment(request);
        }

        // For other payment methods
        Payment payment = paymentMapper.toPayment(request);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setStatus(PaymentStatus.PENDING);
        payment.setTransactionId(PaymentUtils.generateTransactionId());

        linkPaymentToOrder(payment, request.getOrderId());

        payment = paymentRepository.save(payment);
        return paymentMapper.toPaymentResponse(payment);
    }

    @Override
    @Transactional
    public PaymentResponse createMomoPayment(PaymentRequest request) {
        try {
            // Create payment record first
            Payment payment = paymentMapper.toPayment(request);
            payment.setPaymentDate(LocalDateTime.now());
            payment.setStatus(PaymentStatus.PENDING);
            String transactionId = PaymentUtils.generateTransactionId();
            payment.setTransactionId(transactionId);

            linkPaymentToOrder(payment, request.getOrderId());
            payment = paymentRepository.save(payment);

            // Prepare MoMo request
            String requestId = UUID.randomUUID().toString();
            String orderId = PaymentUtils.generateOrderId();
            String orderInfo = request.getOrderInfo() != null ? request.getOrderInfo() : "Thanh toán đơn hàng";

            // Create raw signature data
            String rawHash = "accessKey=" + momoConfig.getAccessKey() +
                    "&amount=" + request.getAmount().longValue() +
                    "&extraData=" +
                    "&ipnUrl=" + momoConfig.getNotifyUrl() +
                    "&orderId=" + orderId +
                    "&orderInfo=" + orderInfo +
                    "&partnerCode=" + momoConfig.getPartnerCode() +
                    "&redirectUrl=" + momoConfig.getReturnUrl() +
                    "&requestId=" + requestId +
                    "&requestType=" + momoConfig.getRequestType();

            String signature = PaymentUtils.computeHmacSha256(rawHash, momoConfig.getSecretKey());

            // Prepare request body for MoMo
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("partnerCode", momoConfig.getPartnerCode());
            requestBody.put("partnerName", "Devteria Shop");
            requestBody.put("storeId", momoConfig.getPartnerCode());
            requestBody.put("requestId", requestId);
            requestBody.put("amount", request.getAmount().longValue());
            requestBody.put("orderId", orderId);
            requestBody.put("orderInfo", orderInfo);
            requestBody.put("redirectUrl", momoConfig.getReturnUrl());
            requestBody.put("ipnUrl", momoConfig.getNotifyUrl());
            requestBody.put("lang", "vi");
            requestBody.put("extraData", "");
            requestBody.put("requestType", momoConfig.getRequestType());
            requestBody.put("signature", signature);

            log.info("Sending MoMo payment request: {}", requestBody);

            // Call MoMo API
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<MomoPaymentResult> response = restTemplate.exchange(
                    momoConfig.getEndpoint(),
                    HttpMethod.POST,
                    entity,
                    MomoPaymentResult.class
            );

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                MomoPaymentResult momoResult = response.getBody();

                // Update payment with MoMo response
                payment.setTransactionId(orderId);
                payment = paymentRepository.save(payment);

                // Create response with payment URL
                PaymentResponse paymentResponse = paymentMapper.toPaymentResponse(payment);
                paymentResponse.setPayUrl(momoResult.getPayUrl());
                paymentResponse.setQrCodeUrl(momoResult.getQrCodeUrl());
                paymentResponse.setOrderInfo(orderInfo);

                return paymentResponse;
            } else {
                throw new AppException(ErrorCode.PAYMENT_GATEWAY_ERROR);
            }

        } catch (Exception e) {
            log.error("Error creating MoMo payment: ", e);
            throw new AppException(ErrorCode.PAYMENT_GATEWAY_ERROR);
        }
    }

    @Override
    @Transactional
    public PaymentResponse processMomoCallback(String partnerCode, String orderId, String requestId,
                                               String amount, String orderInfo, String orderType,
                                               String transId, String payType, String signature) {
        try {
            log.info("Processing MoMo callback - OrderId: {}, Amount: {}", orderId, amount);

            // Verify signature
            String rawHash = "accessKey=" + momoConfig.getAccessKey() +
                    "&amount=" + amount +
                    "&extraData=" +
                    "&message=" +
                    "&orderId=" + orderId +
                    "&orderInfo=" + orderInfo +
                    "&orderType=" + orderType +
                    "&partnerCode=" + partnerCode +
                    "&payType=" + payType +
                    "&requestId=" + requestId +
                    "&responseTime=" +
                    "&resultCode=0" + // Assuming success for signature verification
                    "&transId=" + transId;

            String calculatedSignature = PaymentUtils.computeHmacSha256(rawHash, momoConfig.getSecretKey());

            if (!calculatedSignature.equals(signature)) {
                throw new AppException(ErrorCode.PAYMENT_SIGNATURE_INVALID);
            }

            // Find and update payment
            Payment payment = paymentRepository.findByTransactionId(orderId)
                    .orElseThrow(() -> new AppException(ErrorCode.PAYMENT_NOT_FOUND));

            // Update payment status based on actual result code (you might get this from callback)
            payment.setStatus(PaymentStatus.COMPLETED);
            payment = paymentRepository.save(payment);

            log.info("Payment {} completed successfully", orderId);
            return paymentMapper.toPaymentResponse(payment);

        } catch (Exception e) {
            log.error("Error processing MoMo callback: ", e);
            throw new AppException(ErrorCode.PAYMENT_PROCESSING_ERROR);
        }
    }

    private void linkPaymentToOrder(Payment payment, Long orderId) {
        if (orderId != null) {
            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
            payment.getOrders().add(order);
            order.setPayment(payment);
        }
    }

    // Other service methods (getAllPayments, getPaymentById, etc.) remain the same as previous implementation
    @Override
    @Transactional(readOnly = true)
    public List<PaymentResponse> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(paymentMapper::toPaymentResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentResponse getPaymentById(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new AppException(ErrorCode.PAYMENT_NOT_FOUND));
        return paymentMapper.toPaymentResponse(payment);
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentResponse getPaymentByTransactionId(String transactionId) {
        Payment payment = paymentRepository.findByTransactionId(transactionId)
                .orElseThrow(() -> new AppException(ErrorCode.PAYMENT_NOT_FOUND));
        return paymentMapper.toPaymentResponse(payment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentResponse> getPaymentsByMethod(PaymentMethod method) {
        return paymentRepository.findByPaymentMethod(method)
                .stream()
                .map(paymentMapper::toPaymentResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentResponse> getPaymentsByStatus(PaymentStatus status) {
        return paymentRepository.findByStatus(status)
                .stream()
                .map(paymentMapper::toPaymentResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentResponse> getPaymentsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return paymentRepository.findByPaymentDateBetween(startDate, endDate)
                .stream()
                .map(paymentMapper::toPaymentResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getTotalRevenue(LocalDateTime startDate, LocalDateTime endDate) {
        return paymentRepository.getTotalRevenue(startDate, endDate)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    @Transactional
    public PaymentResponse updatePaymentStatus(Long paymentId, PaymentStatus status) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new AppException(ErrorCode.PAYMENT_NOT_FOUND));
        payment.setStatus(status);
        payment = paymentRepository.save(payment);
        return paymentMapper.toPaymentResponse(payment);
    }

    @Override
    @Transactional
    public void deletePayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new AppException(ErrorCode.PAYMENT_NOT_FOUND));

        if (!payment.getOrders().isEmpty()) {
            throw new AppException(ErrorCode.PAYMENT_HAS_ORDERS);
        }

        paymentRepository.delete(payment);
    }

}