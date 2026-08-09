package com.lucnghinh.laptop_store.controller;


import com.lucnghinh.laptop_store.dto.request.ProductFilterRequest;
import com.lucnghinh.laptop_store.dto.response.ApiResponse;
import com.lucnghinh.laptop_store.dto.response.ProductPageResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import com.lucnghinh.laptop_store.dto.response.ProductDetailResponse;
import com.lucnghinh.laptop_store.dto.request.ProductRequest;
import com.lucnghinh.laptop_store.dto.response.ProductResponse;
import com.lucnghinh.laptop_store.service.ProductService;

import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductService productService;

    @GetMapping("/{id}")
    public ApiResponse<ProductDetailResponse> getProductById(@PathVariable String id) {
        return ApiResponse.<ProductDetailResponse>builder()
                .data(productService.getProductById(id))
                .build();
    }

    @PostMapping
    public ApiResponse<ProductResponse> addProduct(@Valid @ModelAttribute ProductRequest productRequest, @RequestParam("thumbnail") MultipartFile thumbnail) {
        ProductResponse productResponse = productService.addProduct(productRequest, thumbnail);
        return ApiResponse.<ProductResponse>builder()
                .data(productResponse)
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductResponse> updateProductById(@PathVariable String id,@Valid @RequestBody ProductRequest productRequest) {
         ProductResponse productResponse = productService.updateProductById(id, productRequest);
        return ApiResponse.<ProductResponse>builder()
                .data(productResponse)
                .build();
    }


    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteProductById(@PathVariable String id) {
        productService.deleteProductById(id);
        return ApiResponse.<Void>builder()
                .build();
    }

    @GetMapping
    public ApiResponse<ProductPageResponse> getProductsWithPagination(@ModelAttribute ProductFilterRequest filterRequest) {
        return ApiResponse.<ProductPageResponse>builder()
                .data(productService.getProductsWithPagination(filterRequest))
                .build();
    }

    @PutMapping("/{id}/thumbnail")
    public ApiResponse<ProductResponse> updateThumbnail(@PathVariable String id,@RequestParam("file") MultipartFile file) {
        ProductResponse productResponse = productService.updateThumbnail(id, file);
        return ApiResponse.<ProductResponse>builder()
                .data(productResponse)
                .build();
    }
}
