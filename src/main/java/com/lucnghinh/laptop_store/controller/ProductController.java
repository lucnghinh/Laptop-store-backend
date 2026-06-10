package com.lucnghinh.laptop_store.controller;

import com.lucnghinh.laptop_store.dto.ProductDetailResponse;
import com.lucnghinh.laptop_store.dto.ProductRequest;
import com.lucnghinh.laptop_store.dto.ProductResponse;
import com.lucnghinh.laptop_store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ProductResponse addProduct(@RequestBody ProductRequest productRequest) {
        return productService.addProduct(productRequest);
    }

    @PutMapping("/{id}")
    public ProductResponse updateProductById(@PathVariable String id, @RequestBody ProductRequest productRequest) {
        return productService.updateProductById(id, productRequest);
    }


    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable String id) {
        productService.deleteProductById(id);
    }

}
