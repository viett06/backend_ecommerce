package com.devteria.identity_service.controller;

import com.devteria.identity_service.dto.request.CartItemRequest;
import com.devteria.identity_service.dto.response.ApiResponse;
import com.devteria.identity_service.dto.response.CartItemResponse;
import com.devteria.identity_service.service.impl.ICartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartitem")
public class CartItemController {
    @Autowired
    private ICartItemService iCartItemService;
    @PostMapping("/{productId}")
    public ApiResponse<CartItemResponse> addCartItemToCart(@PathVariable Long productId, @RequestBody CartItemRequest cartItemRequest){
        CartItemResponse cartItemResponse = iCartItemService.addItemToCart(productId, cartItemRequest);
        return ApiResponse.<CartItemResponse>builder()
                .result(cartItemResponse)
                .build();
    }
    @DeleteMapping("/{productId}")
    public ApiResponse<Void> deleteCartItemFromCart(@PathVariable Long productId){
        iCartItemService.deleteCartItemFromCart(productId);
        return ApiResponse.<Void>builder()
                .build();
    }
}
