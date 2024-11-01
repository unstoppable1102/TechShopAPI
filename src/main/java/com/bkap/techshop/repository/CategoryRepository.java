package com.bkap.techshop.repository;

import com.bkap.techshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
  List<Category> findByNameContainingIgnoreCase(String name);
  boolean existsByName(String name);
}