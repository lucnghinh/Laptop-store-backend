package com.lucnghinh.laptop_store.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductFilterRequest {
    int pageNumber = 0;
    int size = 10;
    String sortBy = "id";
    String direction = "asc";
    String keyword;
    String brand;
    UUID category;
    BigDecimal minPrice;
    BigDecimal maxPrice;
}
