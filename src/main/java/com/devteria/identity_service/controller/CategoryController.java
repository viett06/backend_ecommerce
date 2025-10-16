package com.devteria.identity_service.controller;

import com.devteria.identity_service.dto.request.CategoryRequest;
import com.devteria.identity_service.dto.request.CategoryUpdateRequest;
import com.devteria.identity_service.dto.response.ApiResponse;
import com.devteria.identity_service.dto.response.CategoryResponse;
import com.devteria.identity_service.service.impl.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ICategoryService iCategoryService;
    @PostMapping
    public ApiResponse<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest){
        CategoryResponse categoryResponse = iCategoryService.createCategory(categoryRequest);
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryResponse)
                .build();
    }
    @GetMapping("/{categoryId}")
    public ApiResponse<CategoryResponse> getCategory(@PathVariable Long categoryId){
        CategoryResponse categoryResponse = iCategoryService.getCategory(categoryId);
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryResponse)
                .build();
    }
    @GetMapping
    public ApiResponse<List<CategoryResponse>> getCategories(){
        List<CategoryResponse> categoryResponses = iCategoryService.getCategories();
        return ApiResponse.<List<CategoryResponse>>builder()
                .result(categoryResponses)
                .build();
    }
    @PutMapping("/{categoryId}")
    public ApiResponse<CategoryResponse> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryUpdateRequest categoryUpdateRequest){
        CategoryResponse categoryResponse = iCategoryService.updateCategory(categoryId, categoryUpdateRequest);
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryResponse)
                .build();
    }
    @DeleteMapping("/{categoryId}")
    public ApiResponse<Void> deleteCategory(@PathVariable Long categoryId){
        iCategoryService.deleteCategory(categoryId);
        return ApiResponse.<Void>builder()
                .build();
    }
}
