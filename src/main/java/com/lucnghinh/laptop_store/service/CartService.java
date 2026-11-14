package com.lucnghinh.laptop_store.service;

import com.lucnghinh.laptop_store.dto.request.AddCartItemRequest;
import com.lucnghinh.laptop_store.dto.response.CartItemResponse;
import com.lucnghinh.laptop_store.dto.response.CartResponse;
import com.lucnghinh.laptop_store.entity.Cart;
import com.lucnghinh.laptop_store.entity.CartItem;
import com.lucnghinh.laptop_store.entity.Product;
import com.lucnghinh.laptop_store.entity.User;
import com.lucnghinh.laptop_store.exception.ErrorCode;
import com.lucnghinh.laptop_store.exception.ResourceNotFoundException;
import com.lucnghinh.laptop_store.repository.CartItemRepository;
import com.lucnghinh.laptop_store.repository.CartRepository;
import com.lucnghinh.laptop_store.repository.ProductRepository;
import com.lucnghinh.laptop_store.repository.UserRepository;
import com.lucnghinh.laptop_store.security.CustomUserDetails;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class CartService {
    CartRepository cartRepository;
    CartItemRepository cartItemRepository;
    ProductRepository productRepository;
    UserRepository userRepository;


    @Transactional
    public CartResponse getCart(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

         CustomUserDetails userDetail = (CustomUserDetails) authentication.getPrincipal();

        Cart cart = cartRepository.findByUser_Id(userDetail.getId()).orElseGet(()-> createCart(userDetail.getUser()));

        List<CartItemResponse> itemResponses = cart.getCartItems() == null
                ? List.of()
                : cart.getCartItems().stream()
                .map(item -> CartItemResponse.builder()
                        .id(item.getId())
                        .productId(item.getProduct().getId())
                        .productName(item.getProduct().getName())
                        .price(item.getProduct().getPrice())
                        .quantity(item.getQuantity())
                        .build())
                .toList();



        CartResponse cartResponse = new CartResponse();
        cartResponse.setUserId(userDetail.getId());
        cartResponse.setCartId(cart.getId());
        cartResponse.setUsername(userDetail.getUsername());
        cartResponse.setCartItems(itemResponses);

        return cartResponse;
    }

    @Transactional
    public void addItemToCart(AddCartItemRequest addCartItemRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetail = (CustomUserDetails) authentication.getPrincipal();

        Cart cart = cartRepository.findByUser_Id(userDetail.getId()).orElseGet(()-> createCart(userDetail.getUser()));

        Product product = productRepository.findById(addCartItemRequest.getProductId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(ErrorCode.PRODUCT_NOT_FOUND));

        if (!product.isActive()) {
            throw new RuntimeException("Product is not available");
        }

        CartItem cartItem = cartItemRepository
                .findByCart_IdAndProduct_Id(cart.getId(), product.getId())
                .orElse(null);

        if (cartItem != null) {
            cartItem.setQuantity(
                    cartItem.getQuantity() + addCartItemRequest.getQuantity()
            );
        } else {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(addCartItemRequest.getQuantity());
        }

        cartItemRepository.save(cartItem);

    }

    private Cart createCart(User user) {
        Cart cart = Cart.builder()
                .user(user)
                .build();

        return  cartRepository.save(cart);
    }

    @Transactional
    public void deleteItem(UUID id){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.USER_USERNAME_NOT_FOUND));

        CartItem cartItem = cartItemRepository
                .findByIdAndCart_User_Id(id, user.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(ErrorCode.CART_ITEM_NOT_FOUND));

        cartItemRepository.delete(cartItem);
    }
}
