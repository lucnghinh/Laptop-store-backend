package com.lucnghinh.laptop_store.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddCartItemRequest {
    String productId;
    int quantity;
}
