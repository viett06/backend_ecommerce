package com.devteria.identity_service.mapper;

import com.devteria.identity_service.dto.request.OrderRequest;
import com.devteria.identity_service.dto.request.OrderUpdateRequest;
import com.devteria.identity_service.dto.response.OrderResponse;
import com.devteria.identity_service.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "orderDate", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "customer", ignore = true)
    Order toOrder(OrderRequest orderRequest);

    @Mapping(source = "orderId", target = "orderId")
    @Mapping(source = "orderDate", target = "orderDate")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "payment", target = "paymentResponse")
    @Mapping(source = "shipment", target = "shipmentResponse")
    @Mapping(source = "orderItems", target = "orderItemResponses")
    OrderResponse toOrderResponse(Order order);

    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "orderDate", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "customer", ignore = true)
    void updateOrderFromRequest(@MappingTarget Order order, OrderUpdateRequest orderUpdateRequest);
}