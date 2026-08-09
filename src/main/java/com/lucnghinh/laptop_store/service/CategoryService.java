package com.lucnghinh.laptop_store.service;

import com.lucnghinh.laptop_store.dto.request.CategoryRequest;
import com.lucnghinh.laptop_store.dto.response.CategoryAdminResponse;
import com.lucnghinh.laptop_store.dto.response.CategoryResponse;
import com.lucnghinh.laptop_store.entity.Category;
import com.lucnghinh.laptop_store.exception.DuplicateResourceException;
import com.lucnghinh.laptop_store.exception.ErrorCode;
import com.lucnghinh.laptop_store.exception.ResourceNotFoundException;
import com.lucnghinh.laptop_store.mapper.CategoryMapper;
import com.lucnghinh.laptop_store.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    public CategoryResponse create(CategoryRequest categoryRequest) {
        if (categoryRepository.existsByName(categoryRequest.getName())) {
            throw new DuplicateResourceException(ErrorCode.CATEGORY_ALREADY_EXISTS);
        }
        Category category = categoryRepository.save(categoryMapper.toCategory(categoryRequest));
        return categoryMapper.toCategoryResponse(category);
    }

    public List<CategoryAdminResponse> getAllCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::toCategoryAdminResponse).toList();
    }

    public List<CategoryResponse> getAllActiveCategories() {
        return categoryRepository.findByActiveTrue().stream().map(categoryMapper::toCategoryResponse).toList();
    }



    public CategoryResponse getById(UUID id) {
        Category category = categoryRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.CATEGORY_NOT_FOUND));
        return categoryMapper.toCategoryResponse(category);
    }

    @Transactional
    public void delete(UUID id) {
        Category category = categoryRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.CATEGORY_NOT_FOUND));

        category.setActive(false);
    }


    public CategoryResponse update(UUID id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.CATEGORY_NOT_FOUND));

        if (!category.getName().equals(categoryRequest.getName())
                && categoryRepository.existsByName(categoryRequest.getName())) {

            throw new DuplicateResourceException(ErrorCode.CATEGORY_ALREADY_EXISTS);
        }

        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());

        categoryRepository.save(category);
        return categoryMapper.toCategoryResponse(category);
    }
}
