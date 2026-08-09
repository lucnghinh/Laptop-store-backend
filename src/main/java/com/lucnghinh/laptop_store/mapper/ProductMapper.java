package com.lucnghinh.laptop_store.mapper;

import com.lucnghinh.laptop_store.dto.request.ProductRequest;
import com.lucnghinh.laptop_store.dto.response.ProductDetailResponse;
import com.lucnghinh.laptop_store.dto.response.ProductResponse;
import com.lucnghinh.laptop_store.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "category",ignore = true)
    Product toProduct(ProductRequest request);

    ProductResponse toProductResponse(Product product);

    @Mapping(target = "category",ignore = true)
    ProductDetailResponse toProductDetailResponse(Product product);

    @Mapping(target = "category",ignore = true)
    List<ProductResponse> toProductResponseList(List<Product> products);
}
