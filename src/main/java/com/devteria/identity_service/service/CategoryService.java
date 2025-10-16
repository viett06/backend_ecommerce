package com.devteria.identity_service.service;

import com.devteria.identity_service.dto.request.CategoryRequest;
import com.devteria.identity_service.dto.request.CategoryUpdateRequest;
import com.devteria.identity_service.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest categoryRequest);
    CategoryResponse getCategory(Long categoryId);
    List<CategoryResponse> getCategories();
    CategoryResponse updateCategory(Long categoryId, CategoryUpdateRequest categoryUpdateRequest);
    void deleteCategory(Long categoryId);
}
