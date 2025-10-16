package com.devteria.identity_service.mapper;

import com.devteria.identity_service.dto.request.CartItemRequest;
import com.devteria.identity_service.dto.response.CartItemResponse;
import com.devteria.identity_service.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    //@Mapping(target = "cartItemId.cartId", source = "cartId")
    //@Mapping(target = "cartItemId.productId", source = "productId")
    CartItem toCartItem(CartItemRequest request);
    @Mapping(target = "cartId", source = "cart.cartId")      // ensure Cart entity has getCartId()
    @Mapping(target = "productId", source = "product.productId")
    //@Mapping(source = "cart", target = "")
    CartItemResponse toCartItemResponse(CartItem cartItem);
    void updateCartItemFromRequest(CartItemRequest request, @MappingTarget CartItem cartItem);

}
