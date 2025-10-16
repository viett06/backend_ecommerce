package com.devteria.identity_service.service;

import com.devteria.identity_service.dto.response.CartResponse;
import com.devteria.identity_service.entity.Customer;

public interface CartService {
    //CartResponse createCart(Customer customer);
    CartResponse getMyCart();
    void deleteCart();

}
