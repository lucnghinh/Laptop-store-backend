package com.lucnghinh.laptop_store.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetailResponse {
    String id;
    String name;
    String description;
    BigDecimal price;
    BigDecimal discountPrice;
    String brand;
    String category;
    String slug;
    String thumbnail;
    int stock;
}
