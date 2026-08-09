package com.lucnghinh.laptop_store.repository;

import com.lucnghinh.laptop_store.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID>
{
    boolean existsByName(String name);

    List<Category> findByActiveTrue();

    Optional<Category> findByIdAndActiveTrue(UUID id);
}
