package com.lucnghinh.laptop_store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private String brand;
    private String category;
    private String slug;
    private int stock;
    private String thumbnail;
    private boolean active;
}
