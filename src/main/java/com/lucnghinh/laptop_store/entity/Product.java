package com.lucnghinh.laptop_store.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private String brand;
    private String category;
    private int stock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String slug;
    private String thumbnail;
    private boolean active;
}
