package com.devteria.identity_service.service.impl;

import com.devteria.identity_service.dto.request.IncreaseInventoryProfileRequest;
import com.devteria.identity_service.dto.request.InventoryProfileRequest;
import com.devteria.identity_service.dto.request.InventoryProfileUpdateRequest;
import com.devteria.identity_service.dto.response.InventoryProfileResponse;
import com.devteria.identity_service.entity.InventoryProfile;
import com.devteria.identity_service.entity.Product;
import com.devteria.identity_service.exception.AppException;
import com.devteria.identity_service.exception.ErrorCode;
import com.devteria.identity_service.mapper.InventoryProfileMapper;
import com.devteria.identity_service.repository.InventoryProfileRepository;
import com.devteria.identity_service.repository.ProductRepository;
import com.devteria.identity_service.service.InventoryProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IInventoryProfileService implements InventoryProfileService {
    @Autowired
    private InventoryProfileRepository inventoryProfileRepository;
    @Autowired
    private InventoryProfileMapper inventoryProfileMapper;
    @Autowired
    private ProductRepository productRepository;
    @Override
    @PreAuthorize("hasAuthority('WRITE_USER')")
    public InventoryProfileResponse createInventoryProfile(Long productId, InventoryProfileRequest inventoryProfileRequest) {
        InventoryProfile inventoryProfile = inventoryProfileMapper.toInventoryProfile(inventoryProfileRequest);
        Product product = productRepository.findById(productId).orElseThrow(()->new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
        inventoryProfile.setProduct(product);
        product.setInventoryProfile(inventoryProfile);
        inventoryProfileRepository.save(inventoryProfile);
        inventoryProfileRepository.flush();
        return inventoryProfileMapper.toInventoryProfileResponse(inventoryProfile);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('WRITE_USER')")
    public InventoryProfileResponse increaseQuantity(Long productId, IncreaseInventoryProfileRequest increaseInventoryProfileRequest) {
        InventoryProfile inventoryProfile = inventoryProfileRepository.findByProductId(productId).orElseThrow(()-> new AppException(ErrorCode.INVENTORY_PROFILE_NOT_EXISTED));
        inventoryProfile.setQuantity(inventoryProfile.getQuantity() + increaseInventoryProfileRequest.getQuantityToAdd());
        inventoryProfileRepository.save(inventoryProfile);
        return inventoryProfileMapper.toInventoryProfileResponse(
                inventoryProfile
        );
    }

    @Override
    public InventoryProfileResponse getInventoryProfile(Long inventoryProfileId) {
        InventoryProfile inventoryProfile = inventoryProfileRepository.findById(inventoryProfileId).orElseThrow(()-> new AppException(ErrorCode.INVENTORY_PROFILE_NOT_EXISTED));
        return inventoryProfileMapper.toInventoryProfileResponse(inventoryProfile);
    }

    @Override
    public Page<InventoryProfileResponse> getInventoryProfiles(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<InventoryProfile> inventoryProfiles = inventoryProfileRepository.findAll(pageable);
        return inventoryProfiles.map(inventoryProfile -> inventoryProfileMapper.toInventoryProfileResponse(inventoryProfile));
    }

    @Override
    @PreAuthorize("hasAuthority('DELETE_USER')")
    public void deleteInventoryProfile(Long inventoryProfileId) {
        inventoryProfileRepository.deleteById(inventoryProfileId);
    }

    @Override
    @PreAuthorize("hasAuthority('WRITE_USER')")
    public InventoryProfileResponse updateInventoryProfile(Long inventoryProfileId, InventoryProfileUpdateRequest inventoryProfileUpdateRequest) {
        InventoryProfile inventoryProfile = inventoryProfileRepository.findById(inventoryProfileId).orElseThrow(()->new AppException(ErrorCode.INVENTORY_PROFILE_NOT_EXISTED));
        inventoryProfileMapper.updateToInventoryProfile(inventoryProfileUpdateRequest, inventoryProfile);
        inventoryProfileRepository.save(inventoryProfile);
        return inventoryProfileMapper.toInventoryProfileResponse(inventoryProfile);
    }

}
