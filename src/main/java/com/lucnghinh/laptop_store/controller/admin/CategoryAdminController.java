package com.lucnghinh.laptop_store.controller.admin;

import com.lucnghinh.laptop_store.dto.response.ApiResponse;
import com.lucnghinh.laptop_store.dto.response.CategoryAdminResponse;
import com.lucnghinh.laptop_store.dto.response.CategoryResponse;
import com.lucnghinh.laptop_store.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/admin/categories")
public class CategoryAdminController {
    CategoryService categoryService;

    @GetMapping
    public ApiResponse<List<CategoryAdminResponse>> getAll() {
        return ApiResponse.<List<CategoryAdminResponse>>builder()
                .data(categoryService.getAllCategories())
                .build();
    }
}
