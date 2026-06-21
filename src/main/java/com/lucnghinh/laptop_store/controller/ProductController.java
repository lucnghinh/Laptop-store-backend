package com.lucnghinh.laptop_store.controller;

import java.util.List;

import com.lucnghinh.laptop_store.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucnghinh.laptop_store.dto.ProductDetailResponse;
import com.lucnghinh.laptop_store.dto.ProductRequest;
import com.lucnghinh.laptop_store.dto.ProductResponse;
import com.lucnghinh.laptop_store.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
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
        apiResponse.setCode(1000);
        apiResponse.setMessage("Success");
        apiResponse.setData(productResponse);
        return apiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductResponse> updateProductById(@PathVariable String id,@Valid @RequestBody ProductRequest productRequest) {
         ProductResponse productResponse = productService.updateProductById(id, productRequest);
         ApiResponse<ProductResponse> apiResponse = new ApiResponse<>();
         apiResponse.setCode(1000);
         apiResponse.setMessage("Success");
         apiResponse.setData(productResponse);
        return apiResponse;
    }


    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable String id) {
        productService.deleteProductById(id);
    }

}
