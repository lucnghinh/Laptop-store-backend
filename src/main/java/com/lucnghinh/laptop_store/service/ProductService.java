package com.lucnghinh.laptop_store.service;

import com.lucnghinh.laptop_store.entity.Product;
import com.lucnghinh.laptop_store.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    public Product getProductById(String id) {
        return productRepo.findById(id).get();
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public void deleteProductById(String id) {
        productRepo.deleteById(id);
    }

    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    public Product updateProductById(String id,Product request) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setDiscountPrice(request.getDiscountPrice());
        product.setBrand(request.getBrand());
        product.setCategory(request.getCategory());
        product.setStock(request.getStock());
        product.setSlug(request.getSlug());
        product.setThumbnail(request.getThumbnail());
        product.setActive(request.isActive());


        return productRepo.save(product);
    }

}
