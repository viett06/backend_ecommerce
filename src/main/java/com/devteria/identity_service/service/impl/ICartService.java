package com.devteria.identity_service.service.impl;

import com.devteria.identity_service.dto.response.CartResponse;
import com.devteria.identity_service.entity.Cart;
import com.devteria.identity_service.entity.Customer;
import com.devteria.identity_service.entity.User;
import com.devteria.identity_service.exception.AppException;
import com.devteria.identity_service.exception.ErrorCode;
import com.devteria.identity_service.mapper.CartMapper;
import com.devteria.identity_service.repository.CartItemRepository;
import com.devteria.identity_service.repository.CartRepository;
import com.devteria.identity_service.repository.CustomerRepository;
import com.devteria.identity_service.repository.UserRepository;
import com.devteria.identity_service.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ICartService implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private CartItemRepository cartItemRepository;
//    @Override
//    public CartResponse createCart(Customer customer) {
//        Cart cart = new Cart();
//        cart.setCustomer(customer);
//        cartRepository.save(cart);
//        return cartMapper.toCartResponse(cart);
//    }

    @Override
    @PreAuthorize("hasAuthority('VIEW')")
    public CartResponse getMyCart() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByName(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        Customer customer = customerRepository.findByUser(user)
                .orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_EXISTED));

        Cart cart = cartRepository.findByCustomer(customer)
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_EXISTED));

        return cartMapper.toCartResponse(cart);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('VIEW')")
    public void deleteCart() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        Customer customer = customerRepository.findByUser(user)
                .orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_EXISTED));

        Cart cart = cartRepository.findByCustomer(customer)
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_EXISTED));

        cartItemRepository.deleteByCart(cart);

    }
}
