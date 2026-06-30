package com.lucnghinh.laptop_store.repository;

import com.lucnghinh.laptop_store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, String> {
    boolean existsByname(String name);
}
