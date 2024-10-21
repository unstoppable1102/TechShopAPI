package com.bkap.techshop.service;

import com.bkap.techshop.entity.Wishlist;

import java.util.List;

public interface WishlistService {

    List<Wishlist> findWishlistsByUserId(long userId);
    List<Long> getWishlistProductIds(long productId);
    List<Wishlist> findAll();
    Wishlist findById(long id);
    Wishlist save(Wishlist wishlist);
    Wishlist update(Wishlist wishlist);
    void delete(long id);
}
