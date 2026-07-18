package com.lucnghinh.laptop_store.dto.request;

import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;


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
    String category;
    BigDecimal minPrice;
    BigDecimal maxPrice;
}
