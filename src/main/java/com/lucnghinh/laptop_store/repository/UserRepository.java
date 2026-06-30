package com.lucnghinh.laptop_store.repository;

import com.lucnghinh.laptop_store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String Email);

    Optional<User> findByUsername(String username);
}
