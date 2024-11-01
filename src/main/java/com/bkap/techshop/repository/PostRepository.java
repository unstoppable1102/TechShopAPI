package com.bkap.techshop.repository;

import com.bkap.techshop.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    boolean existsByPostCategoryId(Long postCategoryId);
    List<Post> findByTitleContainingIgnoreCase(String title);
    boolean existsByTitleIgnoreCase(String title);
}