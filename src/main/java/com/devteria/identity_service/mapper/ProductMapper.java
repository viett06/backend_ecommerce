package com.devteria.identity_service.mapper;

import com.devteria.identity_service.dto.request.ProductRequest;
import com.devteria.identity_service.dto.request.ProductUpdateRequest;
import com.devteria.identity_service.dto.response.ProductResponse;
import com.devteria.identity_service.entity.Product;
import org.mapstruct.*;

import java.lang.annotation.Target;

@Mapper(componentModel = "spring",uses = {CartItemMapper.class, InventoryProfileMapper.class})
public interface ProductMapper {
    @Mapping(target = "cartItems",ignore = true)
    @Mapping(target = "category",ignore = true)
    @Mapping(target = "inventoryProfile",ignore = true)
    Product toProduct(ProductRequest productRequest);
    //@Mapping(source = "cartItems", target = "cartItemResponses")
    @Mapping(source = "inventoryProfile", target = "inventoryProfileResponse")
    ProductResponse toProductResponse(Product product);
    @Mapping(target = "cartItems",ignore = true)
    void updateToProduct(ProductUpdateRequest productUpdateRequest, @MappingTarget Product product);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePatchToProduct(ProductUpdateRequest productUpdateRequest, @MappingTarget Product product);
}
