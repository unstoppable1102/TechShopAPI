package com.bkap.techshop.repository;

import com.bkap.techshop.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    List<Wishlist> findWishlistsByUserId(long userId);
    Wishlist findByUserIdAndProductId(long userId, long productId);

}