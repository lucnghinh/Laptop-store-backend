package com.lucnghinh.laptop_store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private String id;
    private String name;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private String slug;
    private String thumbnail;
}
