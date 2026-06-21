package com.lucnghinh.laptop_store.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @NotBlank(message = "PRODUCT_NAME_REQUIRED")
    @Size(max = 255, message = "PRODUCT_NAME_MAX_LENGTH")
    private String name;

    @Size(max = 2000, message = "PRODUCT_DESCRIPTION_MAX_LENGTH")
    private String description;

    @NotNull(message = "PRODUCT_PRICE_REQUIRED")
    @DecimalMin(value = "0.0", inclusive = false, message = "PRODUCT_PRICE_INVALID")
    private BigDecimal Price;

    @NotNull(message = "PRODUCT_DISCOUNT_PRICE_REQUIRED")
    @DecimalMin(value = "0.0", inclusive = false, message = "PRODUCT_DISCOUNT_PRICE_INVALID")
    private BigDecimal discountPrice;

    @NotBlank(message = "PRODUCT_BRAND_REQUIRED")
    @Size(max = 100, message = "PRODUCT_BRAND_MAX_LENGTH")
    private String brand;

    @NotBlank(message = "PRODUCT_CATEGORY_REQUIRED")
    @Size(max = 100, message = "PRODUCT_CATEGORY_MAX_LENGTH")
    private String category;

    @NotBlank(message = "PRODUCT_SLUG_REQUIRED")
    @Size(max = 255, message = "PRODUCT_SLUG_MAX_LENGTH")
    private String slug;

    @Min(value = 0, message = "PRODUCT_STOCK_INVALID")
    private int stock;

    @Size(max = 500, message = "PRODUCT_THUMBNAIL_MAX_LENGTH")
    private String thumbnail;

    private boolean active;
}
