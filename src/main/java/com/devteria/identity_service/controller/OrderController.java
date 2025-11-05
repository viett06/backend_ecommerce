package com.devteria.identity_service.controller;

import com.devteria.identity_service.dto.request.OrderRequest;
import com.devteria.identity_service.dto.request.OrderUpdateRequest;
import com.devteria.identity_service.dto.response.ApiResponse;
import com.devteria.identity_service.dto.response.OrderResponse;
import com.devteria.identity_service.service.OrderService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {

    OrderService orderService;

    @PostMapping
    public ApiResponse<OrderResponse> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = orderService.createOrder(orderRequest);
        return ApiResponse.<OrderResponse>builder()
                .result(orderResponse)
                .build();
    }

    @GetMapping
    public ApiResponse<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> orderResponses = orderService.getAllOrders();
        return ApiResponse.<List<OrderResponse>>builder()
                .result(orderResponses)
                .build();
    }

    @GetMapping("/my-orders")
    public ApiResponse<List<OrderResponse>> getMyOrders() {
        List<OrderResponse> orderResponses = orderService.getOrdersByCurrentUser();
        return ApiResponse.<List<OrderResponse>>builder()
                .result(orderResponses)
                .build();
    }

    @GetMapping("/{orderId}")
    public ApiResponse<OrderResponse> getOrderById(@PathVariable("orderId") Long orderId) {
        OrderResponse orderResponse = orderService.getOrderById(orderId);
        return ApiResponse.<OrderResponse>builder()
                .result(orderResponse)
                .build();
    }

    @PutMapping("/{orderId}")
    public ApiResponse<OrderResponse> updateOrder(
            @PathVariable("orderId") Long orderId,
            @Valid @RequestBody OrderUpdateRequest orderUpdateRequest) {
        OrderResponse orderResponse = orderService.updateOrder(orderId, orderUpdateRequest);
        return ApiResponse.<OrderResponse>builder()
                .result(orderResponse)
                .build();
    }

    @PutMapping("/{orderId}/cancel")
    public ApiResponse<OrderResponse> cancelOrder(@PathVariable("orderId") Long orderId) {
        OrderResponse orderResponse = orderService.cancelOrder(orderId);
        return ApiResponse.<OrderResponse>builder()
                .result(orderResponse)
                .build();
    }

    @DeleteMapping("/{orderId}")
    public ApiResponse<Void> deleteOrder(@PathVariable("orderId") Long orderId) {
        orderService.deleteOrder(orderId);
        return ApiResponse.<Void>builder()
                .build();
    }
}