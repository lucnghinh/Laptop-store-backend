package com.lucnghinh.laptop_store.repository;

import com.lucnghinh.laptop_store.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {
    Optional<Cart> findByUser_Id(Long userId);
}
