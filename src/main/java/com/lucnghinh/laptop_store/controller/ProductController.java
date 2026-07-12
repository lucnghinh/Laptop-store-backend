package com.lucnghinh.laptop_store.controller;

import java.util.List;

import com.lucnghinh.laptop_store.dto.response.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

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



}
