package com.bkap.techshop.repository;

import com.bkap.techshop.dto.response.PostCategoryResponse;
import com.bkap.techshop.entity.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostCategoryRepository extends JpaRepository<PostCategory, Long> {
}