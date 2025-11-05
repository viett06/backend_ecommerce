package com.devteria.identity_service.service;

import com.devteria.identity_service.dto.request.OrderItemRequest;
import com.devteria.identity_service.dto.request.OrderItemUpdateRequest;
import com.devteria.identity_service.dto.response.OrderItemResponse;

import java.math.BigDecimal;
import java.util.List;

public interface OrderItemService {
    OrderItemResponse addItemToOrder(Long orderId, OrderItemRequest orderItemRequest);
    List<OrderItemResponse> getItemsByOrderId(Long orderId);
    OrderItemResponse getOrderItem(Long orderId, Long productId);
    OrderItemResponse updateOrderItem(Long orderId, Long productId, OrderItemUpdateRequest orderItemUpdateRequest);
    void removeItemFromOrder(Long orderId, Long productId);
    void removeAllItemsFromOrder(Long orderId);
    BigDecimal getOrderTotalAmount(Long orderId);
}