package com.lucnghinh.laptop_store.specification;

import com.lucnghinh.laptop_store.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductSpecification {
    public static Specification<Product> hasName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }

    public static Specification<Product> hasBrand(String brand){
        return (root, query, criteriaBuilder) -> {
            if(brand == null || brand.trim().isEmpty()){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(criteriaBuilder.lower(root.get("brand")), brand.toLowerCase());
        };
    }

    public static Specification<Product> hasCategory(UUID category){
        return ((root, query, criteriaBuilder) ->  {
            if(category == null){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("category").get("Id"), category);
        });
    }

    public static Specification<Product> hasMinPrice(BigDecimal minPrice){
        return (root, query, criteriaBuilder) ->{
            if(minPrice == null){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
        };
    }

    public static Specification<Product> hasMaxPrice(BigDecimal maxPrice){
        return (root, query, criteriaBuilder) ->  {
            if(maxPrice == null){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
        };
    }
}
