package com.lucnghinh.laptop_store.repository;

import com.lucnghinh.laptop_store.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,UUID> {
    Optional<CartItem> findByCart_IdAndProduct_Id(UUID id, String id1);

    Optional<CartItem> findByIdAndCart_User_Id(UUID id, Long userId);
}
