package com.devteria.identity_service.mapper;

import com.devteria.identity_service.dto.request.OrderItemRequest;
import com.devteria.identity_service.dto.request.OrderItemUpdateRequest;
import com.devteria.identity_service.dto.response.OrderItemResponse;
import com.devteria.identity_service.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(target = "order", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "review", ignore = true)
    @Mapping(target = "orderItemId", ignore = true)
    OrderItem toOrderItem(OrderItemRequest orderItemRequest);

    @Mapping(source = "orderItemId.orderId", target = "orderId")
    @Mapping(source = "orderItemId.productId", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(expression = "java(orderItem.getPriceEach().multiply(java.math.BigDecimal.valueOf(orderItem.getQuantity())))", target = "subTotal")
    @Mapping(expression = "java(orderItem.getReview() != null)", target = "reviewed")
    OrderItemResponse toOrderItemResponse(OrderItem orderItem);

    @Mapping(target = "order", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "review", ignore = true)
    @Mapping(target = "orderItemId", ignore = true)
    void updateOrderItemFromRequest(@MappingTarget OrderItem orderItem, OrderItemUpdateRequest orderItemUpdateRequest);
}