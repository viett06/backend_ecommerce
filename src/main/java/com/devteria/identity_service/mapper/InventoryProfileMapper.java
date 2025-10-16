package com.devteria.identity_service.mapper;

import com.devteria.identity_service.dto.request.InventoryProfileRequest;
import com.devteria.identity_service.dto.request.InventoryProfileUpdateRequest;
import com.devteria.identity_service.dto.request.ProductUpdateRequest;
import com.devteria.identity_service.dto.response.InventoryProfileResponse;
import com.devteria.identity_service.entity.InventoryProfile;
import com.devteria.identity_service.entity.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface InventoryProfileMapper {
    @Mapping(target = "product",ignore = true)
    InventoryProfile toInventoryProfile(InventoryProfileRequest inventoryProfileRequest);
    InventoryProfileResponse toInventoryProfileResponse(InventoryProfile inventoryProfile);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    InventoryProfile updateToInventoryProfile(InventoryProfileUpdateRequest inventoryProfileUpdateRequest, @MappingTarget InventoryProfile inventoryProfile);

}
