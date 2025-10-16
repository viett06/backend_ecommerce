package com.devteria.identity_service.service.impl;

import com.devteria.identity_service.dto.request.CategoryRequest;
import com.devteria.identity_service.dto.request.CategoryUpdateRequest;
import com.devteria.identity_service.dto.response.CategoryResponse;
import com.devteria.identity_service.entity.Category;
import com.devteria.identity_service.exception.AppException;
import com.devteria.identity_service.exception.ErrorCode;
import com.devteria.identity_service.mapper.CategoryMapper;
import com.devteria.identity_service.repository.CategoryRepository;
import com.devteria.identity_service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICategoryService implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    @PreAuthorize("hasAuthority('WRITE_USER')")
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category category = categoryMapper.toCategory(categoryRequest);
        categoryRepository.save(category);
        categoryRepository.flush();
        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public CategoryResponse getCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));
        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public List<CategoryResponse> getCategories() {
        List<Category> categoryResponses = categoryRepository.findAll();
        return categoryResponses.stream().map(category -> categoryMapper.toCategoryResponse(category)).toList();
    }

    @Override
    @PreAuthorize("hasAuthority('DELETE_USER')")
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    @PreAuthorize("hasAuthority('WRITE_USER')")
    public CategoryResponse updateCategory(Long categoryId, CategoryUpdateRequest categoryUpdateRequest) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));
        categoryMapper.updateToCategoryResponse(category, categoryUpdateRequest);
        categoryRepository.save(category);
        categoryRepository.flush();
        return categoryMapper.toCategoryResponse(category);
    }
}
