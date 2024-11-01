package com.bkap.techshop.repository;

import com.bkap.techshop.entity.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostCategoryRepository extends JpaRepository<PostCategory, Long> {
    boolean existsByName(String name);
    List<PostCategory> findByNameContainingIgnoreCase(String name);
}