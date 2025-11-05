package com.devteria.identity_service.mapper;

import com.devteria.identity_service.dto.request.PaymentRequest;
import com.devteria.identity_service.dto.response.PaymentResponse;
import com.devteria.identity_service.entity.Payment;
import com.devteria.identity_service.enums.PaymentMethod;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(target = "paymentId", ignore = true)
    @Mapping(target = "paymentDate", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "transactionId", ignore = true)
    @Mapping(target = "orderInfo", source = "orderInfo")
    @Mapping(source = "paymentMethod", target = "paymentMethod")
    Payment toPayment(PaymentRequest request);

    @Mapping(target = "orderIds", expression = "java(mapOrdersToOrderIds(payment.getOrders()))")
    PaymentResponse toPaymentResponse(Payment payment);

    // Map PaymentMethod enum to String
    default String map(PaymentMethod paymentMethod) {
        return paymentMethod != null ? paymentMethod.name() : null;
    }

    // Helper method to extract orderIds from orders
    default List<Long> mapOrdersToOrderIds(java.util.Set<com.devteria.identity_service.entity.Order> orders) {
        if (orders == null || orders.isEmpty()) {
            return java.util.Collections.emptyList();
        }
        return orders.stream()
                .map(com.devteria.identity_service.entity.Order::getOrderId)
                .collect(Collectors.toList());
    }
}