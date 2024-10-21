package com.bkap.techshop.repository;

import com.bkap.techshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductNameContainingIgnoreCase(String name);
    boolean existsByCategoryId(Long categoryId);
}