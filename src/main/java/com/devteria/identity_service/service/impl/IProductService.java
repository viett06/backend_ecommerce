package com.devteria.identity_service.service.impl;

import com.devteria.identity_service.dto.request.ProductRequest;
import com.devteria.identity_service.dto.request.ProductUpdateRequest;
import com.devteria.identity_service.dto.response.ProductResponse;
import com.devteria.identity_service.entity.Category;
import com.devteria.identity_service.entity.InventoryProfile;
import com.devteria.identity_service.entity.Product;
import com.devteria.identity_service.exception.AppException;
import com.devteria.identity_service.exception.ErrorCode;
import com.devteria.identity_service.mapper.ProductMapper;
import com.devteria.identity_service.repository.CategoryRepository;
import com.devteria.identity_service.repository.ProductRepository;
import com.devteria.identity_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IProductService implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    @Transactional
    @PreAuthorize("hasAuthority('WRITE_USER')")
    public ProductResponse createProduct(Long categoryId, ProductRequest productRequest) {
        Product product = productMapper.toProduct(productRequest);
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));
        product.setCategory(category);
        InventoryProfile inventoryProfile = new InventoryProfile();
        inventoryProfile.setQuantity(0);
        inventoryProfile.setProduct(product);
        product.setInventoryProfile(inventoryProfile);
        productRepository.save(product);
        productRepository.flush();
        return productMapper.toProductResponse(product);
    }

    @Override
    public ProductResponse getProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(()->
                new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
        return productMapper.toProductResponse(product);

    }
    @Override
    @Transactional
    @PreAuthorize("hasAuthority('WRITE_USER')")
    public ProductResponse updateProduct(Long productId, ProductUpdateRequest productUpdateRequest) {
        Product product = productRepository.findById(productId).orElseThrow(()-> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
        productMapper.updateToProduct(productUpdateRequest, product);
        productRepository.save(product);
        productRepository.flush();
        return productMapper.toProductResponse(product);
    }
    @Override
    @Transactional
    @PreAuthorize("hasAuthority('WRITE_USER')")
    public ProductResponse patchProduct(Long productId, ProductUpdateRequest productUpdateRequest) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
        // Chỉ update các field không null
        productMapper.updatePatchToProduct(productUpdateRequest, product);
        productRepository.save(product);
        productRepository.flush();
        return productMapper.toProductResponse(product);
    }

    @Override
    public Page<ProductResponse> getAllProduct(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.map(product -> productMapper.toProductResponse(product));
    }

    @Override
    public Page<ProductResponse> searchProduct(int page, int size, String keyword) {
        Pageable pageable = PageRequest.of(page, size);
        // tìm sản phẩm trùng tên
        Page<Product> exactMatches = productRepository.findByExactName(keyword, pageable);
        if (exactMatches.hasContent()) {
            // Nếu có kết quả trùng tên → trả về luôn
            return exactMatches.map(productMapper::toProductResponse);
        }
        // Nếu không có, tìm theo chữ cái đầu
        String firstLetter = keyword.substring(0, 1);
        Page<Product> similarProducts = productRepository.findByFirstLetter(firstLetter, pageable);
        return similarProducts.map(productMapper::toProductResponse);
    }

    @Override
    @PreAuthorize("hasAuthority('DELETE_USER')")
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
