package com.lucnghinh.laptop_store.controller;

import com.lucnghinh.laptop_store.dto.request.CategoryRequest;
import com.lucnghinh.laptop_store.dto.response.ApiResponse;
import com.lucnghinh.laptop_store.dto.response.CategoryResponse;
import com.lucnghinh.laptop_store.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/categories")
public class CategoryController {
    CategoryService categoryService;

    @PostMapping("/create")
    public ApiResponse<CategoryResponse> create(@RequestBody CategoryRequest categoryRequest) {
        return ApiResponse.<CategoryResponse>builder()
                .data(categoryService.create(categoryRequest))
                .build();
    }

    @GetMapping
    public ApiResponse<List<CategoryResponse>> getAll() {
        return ApiResponse.<List<CategoryResponse>>builder()
                .data(categoryService.getAllActiveCategories())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<CategoryResponse> getById(@PathVariable UUID id) {
        return ApiResponse.<CategoryResponse>builder()
                .data(categoryService.getById(id))
                .build();
    }

    @DeleteMapping("{id}")
    public ApiResponse<Void> deleteById(@PathVariable UUID id) {
        categoryService.delete(id);
        return ApiResponse.<Void>builder()
                .build();
    }

    @PostMapping("{id}/update")
    public ApiResponse<CategoryResponse> update(@PathVariable UUID id,@RequestBody CategoryRequest categoryRequest) {
        return ApiResponse.<CategoryResponse>builder()
                .data(categoryService.update(id, categoryRequest))
                .build();
    }


}
