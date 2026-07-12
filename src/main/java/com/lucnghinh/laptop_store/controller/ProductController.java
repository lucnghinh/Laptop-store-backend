package com.lucnghinh.laptop_store.controller;


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

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductService productService;

    @GetMapping("/{id}")
    public ProductDetailResponse getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public ApiResponse<ProductResponse> addProduct(@Valid @RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.addProduct(productRequest);
        ApiResponse<ProductResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(productResponse);
        return apiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductResponse> updateProductById(@PathVariable String id,@Valid @RequestBody ProductRequest productRequest) {
         ProductResponse productResponse = productService.updateProductById(id, productRequest);
         ApiResponse<ProductResponse> apiResponse = new ApiResponse<>();
         apiResponse.setData(productResponse);
        return apiResponse;
    }


    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable String id) {
        productService.deleteProductById(id);
    }

    @GetMapping
    public ApiResponse<ProductPageResponse> getProductsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return ApiResponse.<ProductPageResponse>builder()
                .data(productService.getProductsWithPagination(page, size, sortBy, direction))
                .build();
    }

}
