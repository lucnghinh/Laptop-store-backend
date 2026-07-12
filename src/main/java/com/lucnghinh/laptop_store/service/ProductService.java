package com.lucnghinh.laptop_store.service;


import com.lucnghinh.laptop_store.dto.response.ProductPageResponse;
import com.lucnghinh.laptop_store.exception.AppException;
import com.lucnghinh.laptop_store.exception.DuplicateResourceException;
import com.lucnghinh.laptop_store.exception.ErrorCode;
import com.lucnghinh.laptop_store.mapper.ProductMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.lucnghinh.laptop_store.dto.response.ProductDetailResponse;
import com.lucnghinh.laptop_store.dto.request.ProductRequest;
import com.lucnghinh.laptop_store.dto.response.ProductResponse;
import com.lucnghinh.laptop_store.entity.Product;
import com.lucnghinh.laptop_store.exception.ResourceNotFoundException;
import com.lucnghinh.laptop_store.repository.ProductRepository;

import java.util.Set;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class ProductService {
    ProductRepository productRepository;
    ProductMapper productMapper;

    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of(
            "id",
            "name",
            "price",
            "discountPrice",
            "brand",
            "category",
            "stock",
            "createdAt"
    );

    public ProductDetailResponse getProductById(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ErrorCode.PRODUCT_NOT_FOUND));
        return productMapper.toProductDetailResponse(product);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProductById(String id) {
        productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.PRODUCT_NOT_FOUND));
        productRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse  addProduct(ProductRequest request) {
        if (productRepository.existsByname(request.getName())) {
            throw new DuplicateResourceException(ErrorCode.PRODUCT_ALREADY_EXISTS);
        }

        Product product = productMapper.toProduct(request);
        product = productRepository.save(product);
        return productMapper.toProductResponse(product);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse updateProductById(String id, ProductRequest request) {
        productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.PRODUCT_NOT_FOUND));

        Product mappedProduct = productMapper.toProduct(request);
        mappedProduct = productRepository.save(mappedProduct);
        return productMapper.toProductResponse(mappedProduct);
    }

    public ProductPageResponse getProductsWithPagination(int pageNumber, int size, String sortBy, String direction) {

        if(!ALLOWED_SORT_FIELDS.contains(sortBy)) {
            throw new AppException(ErrorCode.INVALID_SORT_FIELD);
        }

        if (!direction.equalsIgnoreCase("asc") && !direction.equalsIgnoreCase("desc")) {
            throw new AppException(ErrorCode.INVALID_SORT_DIRECTION);
        }

        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, size, sort);

        Page<Product> productPage = productRepository.findByActive(true, pageable);

        return ProductPageResponse.builder()
                .products(productMapper.toProductResponseList(productPage.getContent()))
                .size(size)
                .page(pageNumber)
                .totalElements(productPage.getTotalElements())
                .totalPages(productPage.getTotalPages())
                .first(productPage.isFirst())
                .last(productPage.isLast())
                .build();
    }

}
