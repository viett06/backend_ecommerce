package com.devteria.identity_service.service;

import com.devteria.identity_service.dto.request.OrderRequest;
import com.devteria.identity_service.dto.request.OrderUpdateRequest;
import com.devteria.identity_service.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);
    List<OrderResponse> getAllOrders();
    OrderResponse getOrderById(Long orderId);
    List<OrderResponse> getOrdersByCurrentUser();
    OrderResponse updateOrder(Long orderId, OrderUpdateRequest orderUpdateRequest);
    void deleteOrder(Long orderId);
    OrderResponse cancelOrder(Long orderId);
}