package com.lucnghinh.laptop_store.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


import java.util.List;
import java.util.UUID;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartResponse {
    UUID cartId;
    Long userId;
    String username;
    List<CartItemResponse> cartItems;


}
