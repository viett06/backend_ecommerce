package com.devteria.identity_service.controller;

import com.devteria.identity_service.dto.request.IncreaseInventoryProfileRequest;
import com.devteria.identity_service.dto.request.InventoryProfileRequest;
import com.devteria.identity_service.dto.request.InventoryProfileUpdateRequest;
import com.devteria.identity_service.dto.response.ApiResponse;
import com.devteria.identity_service.dto.response.InventoryProfileResponse;
import com.devteria.identity_service.dto.response.PagedResponse;
import com.devteria.identity_service.service.impl.IInventoryProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryProfileController {
    @Autowired
    private IInventoryProfileService iInventoryProfileService;
    @PostMapping("/product/{productId}")
    public ApiResponse<InventoryProfileResponse> createInventoryProfile(@PathVariable Long productId, @RequestBody InventoryProfileRequest inventoryProfileRequest){
        InventoryProfileResponse inventoryProfileResponse = iInventoryProfileService.createInventoryProfile(productId, inventoryProfileRequest);
        return ApiResponse.<InventoryProfileResponse>builder()
                .result(inventoryProfileResponse)
                .build();
    }
    @GetMapping("/{inventoryProfileId}")
    public ApiResponse<InventoryProfileResponse> getInventoryProfile(@PathVariable Long inventoryProfileId){
        InventoryProfileResponse inventoryProfileResponse = iInventoryProfileService.getInventoryProfile(inventoryProfileId);
        return ApiResponse.<InventoryProfileResponse>builder()
                .result(inventoryProfileResponse)
                .build();
    }
    @GetMapping
    public ApiResponse<PagedResponse<InventoryProfileResponse>> getInventoryProfiles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size){
        Page<InventoryProfileResponse> inventoryProfileResponses = iInventoryProfileService.getInventoryProfiles(page, size);
        PagedResponse<InventoryProfileResponse> pageInventoryProfileResponses = PagedResponse.<InventoryProfileResponse>builder()
                .items(inventoryProfileResponses.getContent())
                .currentPage(inventoryProfileResponses.getNumber())
                .totalItems(inventoryProfileResponses.getTotalElements())
                .totalPages(inventoryProfileResponses.getTotalPages())
                .build();
        return ApiResponse.<PagedResponse<InventoryProfileResponse>>builder()
                .result(pageInventoryProfileResponses)
                .build();
    }
    @PatchMapping("/product/{productId}")
    public ApiResponse<InventoryProfileResponse> increaseQuantity(@PathVariable Long productId,@RequestBody IncreaseInventoryProfileRequest increaseInventoryProfileRequest){
        InventoryProfileResponse inventoryProfileResponse = iInventoryProfileService.increaseQuantity(productId,increaseInventoryProfileRequest);
        return ApiResponse.<InventoryProfileResponse>builder()
                .result(inventoryProfileResponse)
                .build();
    }
    @PutMapping("/{inventoryProfileId}")
    public ApiResponse<InventoryProfileResponse> updateInventoryProfile(@PathVariable Long inventoryProfileId, @RequestBody InventoryProfileUpdateRequest inventoryProfileUpdateRequest){
        InventoryProfileResponse inventoryProfileResponse = iInventoryProfileService.updateInventoryProfile(inventoryProfileId, inventoryProfileUpdateRequest);
        return ApiResponse.<InventoryProfileResponse>builder()
                .result(inventoryProfileResponse)
                .build();
    }
    @DeleteMapping("/{inventoryProfileId}")
    public ApiResponse<Void> deleteInventoryProfile(@PathVariable Long inventoryProfileId){
        iInventoryProfileService.deleteInventoryProfile(inventoryProfileId);
        return ApiResponse.<Void>builder()
                .build();
    }
}
