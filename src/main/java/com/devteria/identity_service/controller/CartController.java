package com.devteria.identity_service.controller;

import com.devteria.identity_service.dto.response.ApiResponse;
import com.devteria.identity_service.dto.response.CartResponse;
import com.devteria.identity_service.service.impl.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICartService iCartService;
    @GetMapping
    public ApiResponse<CartResponse> getMyCart(){
        CartResponse cartResponse = iCartService.getMyCart();
        return ApiResponse.<CartResponse>builder()
                .result(cartResponse)
                .build();
    }
    @DeleteMapping("/clear")
    public ApiResponse<Void> clearCart() {
        iCartService.deleteCart();
        return ApiResponse.<Void>builder().build();
    }

}
