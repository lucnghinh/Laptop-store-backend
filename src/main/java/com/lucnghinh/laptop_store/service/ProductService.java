package com.lucnghinh.laptop_store.service;

import java.util.List;

import com.lucnghinh.laptop_store.exception.DuplicateProductException;
import com.lucnghinh.laptop_store.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucnghinh.laptop_store.dto.ProductDetailResponse;
import com.lucnghinh.laptop_store.dto.ProductRequest;
import com.lucnghinh.laptop_store.dto.ProductResponse;
import com.lucnghinh.laptop_store.entity.Product;
import com.lucnghinh.laptop_store.exception.ResourceNotFoundException;
import com.lucnghinh.laptop_store.repository.ProductRepo;


@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    public ProductDetailResponse getProductById(String id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(ErrorCode.PRODUCT_DOES_NOT_EXIST));

        ProductDetailResponse productDetailResponse = new ProductDetailResponse(
                product.getId(),
        product.getName(),
        product.getDescription(),
        product.getPrice(),
        product.getDiscountPrice(),
        product.getBrand(),
        product.getCategory(),
        product.getSlug(),
        product.getThumbnail(),
                product.getStock()
        );

        return productDetailResponse;

    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepo.findAll();

        return products.stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getDiscountPrice(),
                        product.getSlug(),
                        product.getThumbnail()
                )).toList();
    }

    public void deleteProductById(String id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.PRODUCT_DOES_NOT_EXIST));
        productRepo.deleteById(id);
    }

    public ProductResponse  addProduct(ProductRequest request) {
        Product product = new Product();

        if (productRepo.existsByname(request.getName())) {
            throw new DuplicateProductException(ErrorCode.PRODUCT_EXISTED);
        }
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setDiscountPrice(request.getDiscountPrice());
        product.setBrand(request.getBrand());
        product.setCategory(request.getCategory());
        product.setStock(request.getStock());
        product.setSlug(request.getSlug());
        product.setThumbnail(request.getThumbnail());
        product.setActive(request.isActive());

        Product savedProduct = productRepo.save(product);
        return mapToProductResponse(savedProduct);
    }

    public ProductResponse updateProductById(String id, ProductRequest request) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.PRODUCT_DOES_NOT_EXIST));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setDiscountPrice(request.getDiscountPrice());
        product.setBrand(request.getBrand());
        product.setCategory(request.getCategory());
        product.setStock(request.getStock());
        product.setSlug(request.getSlug());
        product.setThumbnail(request.getThumbnail());
        product.setActive(request.isActive());


        Product updatedProduct = productRepo.save(product);
        return mapToProductResponse(updatedProduct);
    }

    private ProductResponse mapToProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDiscountPrice(),
                product.getSlug(),
                product.getThumbnail()
        );
    }

}
