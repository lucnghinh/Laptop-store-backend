package com.lucnghinh.laptop_store.mapper;

import com.lucnghinh.laptop_store.dto.request.CategoryRequest;
import com.lucnghinh.laptop_store.dto.response.CategoryAdminResponse;
import com.lucnghinh.laptop_store.dto.response.CategoryResponse;
import com.lucnghinh.laptop_store.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryRequest categoryRequest);

    CategoryResponse toCategoryResponse(Category category);

    CategoryAdminResponse toCategoryAdminResponse(Category category);
}
