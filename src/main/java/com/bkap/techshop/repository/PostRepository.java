package com.bkap.techshop.repository;

import com.bkap.techshop.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    boolean existsByPostCategoryId(Long postCategoryId);
}