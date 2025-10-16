package com.devteria.identity_service.mapper;

import com.devteria.identity_service.dto.request.CategoryRequest;
import com.devteria.identity_service.dto.request.CategoryUpdateRequest;
import com.devteria.identity_service.dto.response.CategoryResponse;
import com.devteria.identity_service.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.lang.annotation.Target;

@Mapper(componentModel = "spring",uses = ProductMapper.class)
public interface CategoryMapper {
    @Mapping(target = "products", ignore = true)
    Category toCategory(CategoryRequest categoryRequest);
    CategoryResponse toCategoryResponse(Category category);
    @Mapping(target = "products",ignore = true)
    void updateToCategoryResponse(@MappingTarget Category category, CategoryUpdateRequest categoryUpdateRequest);
}
