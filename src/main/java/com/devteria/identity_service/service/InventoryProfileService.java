package com.devteria.identity_service.service;

import com.devteria.identity_service.dto.request.IncreaseInventoryProfileRequest;
import com.devteria.identity_service.dto.request.InventoryProfileRequest;
import com.devteria.identity_service.dto.request.InventoryProfileUpdateRequest;
import com.devteria.identity_service.dto.response.InventoryProfileResponse;
import com.devteria.identity_service.entity.InventoryProfile;
import org.springframework.data.domain.Page;

public interface InventoryProfileService {
    InventoryProfileResponse createInventoryProfile(Long productId, InventoryProfileRequest inventoryProfileRequest);
    InventoryProfileResponse increaseQuantity(Long productId, IncreaseInventoryProfileRequest inventoryProfileRequest);
    InventoryProfileResponse getInventoryProfile(Long inventoryProfileId);
    Page<InventoryProfileResponse> getInventoryProfiles(int page, int size);
    void deleteInventoryProfile(Long inventoryProfileId);
    InventoryProfileResponse updateInventoryProfile(Long inventoryProfileId, InventoryProfileUpdateRequest inventoryProfileUpdateRequest);
}
