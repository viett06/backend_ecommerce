package com.devteria.identity_service.mapper;

import com.devteria.identity_service.dto.response.CartResponse;
import com.devteria.identity_service.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CartItemMapper.class)
public interface CartMapper {
    //@Mapping(target = "customer",ignore = true)
    @Mapping(source = "cartItems", target = "cartItemResponse")
    CartResponse toCartResponse(Cart cart);
}
