package com.devteria.identity_service.controller;

import com.devteria.identity_service.dto.request.ProductRequest;
import com.devteria.identity_service.dto.request.ProductUpdateRequest;
import com.devteria.identity_service.dto.response.ApiResponse;
import com.devteria.identity_service.dto.response.PagedResponse;
import com.devteria.identity_service.dto.response.ProductResponse;
import com.devteria.identity_service.service.impl.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService iProductService;
    @PostMapping("/category/{categoryId}")
    public ApiResponse<ProductResponse> createProduct(@PathVariable Long categoryId, @RequestBody ProductRequest productRequest){
        ProductResponse productResponse = iProductService.createProduct(categoryId, productRequest);
        return ApiResponse.<ProductResponse>builder()
                .result(productResponse)
                .build();
    }
    @GetMapping("/{productId}")
    public ApiResponse<ProductResponse> getProduct(@PathVariable Long productId){
        ProductResponse productResponse = iProductService.getProduct(productId);
        return ApiResponse.<ProductResponse>builder()
                .result(productResponse)
                .build();
    }
    @PutMapping("/{productId}")
    public ApiResponse<ProductResponse> updateProduct(@PathVariable Long productId, @RequestBody ProductUpdateRequest productUpdateRequest){
        ProductResponse productResponse = iProductService.updateProduct(productId, productUpdateRequest);
        return ApiResponse.<ProductResponse>builder()
                .result(productResponse)
                .build();
    }
    @PatchMapping("/{productId}")
    public ApiResponse<ProductResponse> patchProduct(@PathVariable Long productId, @RequestBody ProductUpdateRequest productUpdateRequest){
        ProductResponse productResponse = iProductService.patchProduct(productId, productUpdateRequest);
        return ApiResponse.<ProductResponse>builder()
                .result(productResponse)
                .build();
    }
    @GetMapping("/getall")
    public ApiResponse<PagedResponse<ProductResponse>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size) {
        Page<ProductResponse> productPage = iProductService.getAllProduct(page, size);
        PagedResponse<ProductResponse> response = PagedResponse.<ProductResponse>builder()
                .items(productPage.getContent())
                .currentPage(productPage.getNumber())
                .totalItems(productPage.getTotalElements())
                .totalPages(productPage.getTotalPages())
                .build();
        return ApiResponse.<PagedResponse<ProductResponse>>builder()
                .result(response)
                .build();
    }
    @GetMapping("/search")
    public ApiResponse<PagedResponse<ProductResponse>> searchProduct(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam String keyword){
        Page<ProductResponse> productResponses = iProductService.searchProduct(page, size, keyword);
        PagedResponse<ProductResponse> productPage = PagedResponse.<ProductResponse>builder()
                .items(productResponses.getContent())
                .currentPage(productResponses.getNumber())
                .totalItems(productResponses.getTotalElements())
                .totalPages(productResponses.getTotalPages())
                .build();
        return ApiResponse.<PagedResponse<ProductResponse>>builder()
                .result(productPage)
                .build();
    }
    @DeleteMapping("/{productId}")
    public ApiResponse<Void> deleteProduct(@PathVariable Long productId){
        iProductService.deleteProduct(productId);
        return ApiResponse.<Void>builder()
                .build();
    }

    }
