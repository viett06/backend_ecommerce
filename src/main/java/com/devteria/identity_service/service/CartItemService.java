package com.devteria.identity_service.service;


import com.devteria.identity_service.dto.request.CartItemRequest;
import com.devteria.identity_service.dto.response.CartItemResponse;

public interface CartItemService {
    CartItemResponse addItemToCart(Long productId, CartItemRequest request);
    void deleteCartItemFromCart(Long productId);
}
