package com.devteria.identity_service.service.impl;

import com.devteria.identity_service.dto.request.CartItemRequest;
import com.devteria.identity_service.dto.response.CartItemResponse;
import com.devteria.identity_service.entity.*;
import com.devteria.identity_service.exception.AppException;
import com.devteria.identity_service.exception.ErrorCode;
import com.devteria.identity_service.mapper.CartItemMapper;
import com.devteria.identity_service.repository.*;
import com.devteria.identity_service.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ICartItemService implements CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CartItemMapper cartItemMapper;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    @Transactional
    @PreAuthorize("hasAuthority('VIEW')")
    public CartItemResponse addItemToCart(Long productId, CartItemRequest request) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Customer customer = customerRepository.findByUser(user)
                .orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_EXISTED));
        Cart cart = cartRepository.findByCustomer(customer)
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_EXISTED));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
        CartItemId cartItemId = new CartItemId(cart.getCartId(), product.getProductId());

        Optional<CartItem> optional = cartItemRepository.findById(cartItemId);
        CartItem cartItem;
        //LocalDateTime now = LocalDateTime.now();
        if (optional.isPresent()) {
            // Nếu đã tồn tại, cập nhật quantity (ví dụ cộng thêm)
            cartItem = optional.get();
            long newQty = (cartItem.getQuantity() == null ? 0L : cartItem.getQuantity()) +
                    (request.getQuantity() == null ? 0L : request.getQuantity());
            cartItem.setQuantity(newQty);
           // cartItem.setUpdateAt(now);
        } else {
            // Tạo mới CartItem
            cartItem = new CartItem();
            cartItem.setCartItemId(cartItemId);
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(request.getQuantity() == null ? 0L : request.getQuantity());
            //cartItem.setCreateAt(now);
            //cartItem.setUpdateAt(now);
        }
        cartItem = cartItemRepository.save(cartItem); // save (insert or update)
        cartItemRepository.flush();
        return cartItemMapper.toCartItemResponse(cartItem);
    }
    @Transactional
    @Override
    @PreAuthorize("hasAuthority('VIEW')")
    public void deleteCartItemFromCart(Long productId) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Customer customer = customerRepository.findByUser(user)
                .orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_EXISTED));
        Cart cart = cartRepository.findByCustomer(customer)
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_EXISTED));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
        CartItemId cartItemId = new CartItemId(cart.getCartId(), product.getProductId());
        if (!cartItemRepository.existsById(cartItemId)) {
            throw new AppException(ErrorCode.CART_ITEM_NOT_EXISTED);
        }
         cartItemRepository.deleteById(cartItemId);
    }
}
