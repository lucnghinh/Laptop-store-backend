package com.lucnghinh.laptop_store.dto.response;

import lombok.AccessLevel;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CartItemResponse {
    UUID id;
    String productId;
    UUID cartId;
    String productName;
    BigDecimal price;
    int quantity;
}
