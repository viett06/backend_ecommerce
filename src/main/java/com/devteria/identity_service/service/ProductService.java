package com.devteria.identity_service.service;

import com.devteria.identity_service.dto.request.ProductRequest;
import com.devteria.identity_service.dto.request.ProductUpdateRequest;
import com.devteria.identity_service.dto.response.ProductResponse;
import org.springframework.data.domain.Page;


public interface ProductService {
    ProductResponse createProduct(Long categoryId, ProductRequest productRequest);
    ProductResponse getProduct(Long productId);
    ProductResponse updateProduct(Long productId, ProductUpdateRequest productUpdateRequest);
    ProductResponse  patchProduct(Long productId, ProductUpdateRequest productUpdateRequest);
    Page<ProductResponse> getAllProduct(int page, int size);
    Page<ProductResponse> searchProduct(int page, int size, String keyword);
    void deleteProduct(Long productId);

}
