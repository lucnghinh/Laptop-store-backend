package com.lucnghinh.laptop_store.controller;

import com.lucnghinh.laptop_store.dto.request.AddCartItemRequest;
import com.lucnghinh.laptop_store.dto.response.ApiResponse;
import com.lucnghinh.laptop_store.dto.response.CartResponse;
import com.lucnghinh.laptop_store.service.CartService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("api/cart")
public class CartController {
    CartService cartService;

    @GetMapping("/getCart")
    public ApiResponse<CartResponse> getCart() {
        return ApiResponse.<CartResponse>builder()
                .data(cartService.getCart())
                .build();
    }

    @PostMapping("/add")
    public ApiResponse<Void> addCartItem(@Valid @RequestBody AddCartItemRequest addCartItemRequest) {
        cartService.addItemToCart(addCartItemRequest);
        return ApiResponse.<Void>builder()
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteCartItem(@PathVariable UUID id) {
        cartService.deleteItem(id);
        return ApiResponse.<Void>builder()
                .build();
    }
}
