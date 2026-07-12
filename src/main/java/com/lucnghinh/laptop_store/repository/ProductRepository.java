package com.lucnghinh.laptop_store.repository;

import com.lucnghinh.laptop_store.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    boolean existsByname(String name);

    Page<Product> findByActive(Boolean active, Pageable pageable);
}
