package com.bkap.techshop.service.impl;

import com.bkap.techshop.entity.Wishlist;
import com.bkap.techshop.exception.AppException;
import com.bkap.techshop.exception.ErrorCode;
import com.bkap.techshop.repository.WishlistRepository;
import com.bkap.techshop.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;

    @Override
    public List<Wishlist> findWishlistsByUserId(long userId) {
        return List.of();
    }

    @Override
    public List<Long> getWishlistProductIds(long productId) {
        return List.of();
    }

    @Override
    public List<Wishlist> findAll() {
        return wishlistRepository.findAll();
    }

    @Override
    public Wishlist findById(long id) {
        return wishlistRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.WISHLIST_NOT_FOUND));
    }

    @Override
    public Wishlist save(Wishlist wishlist) {
          return wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist update(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    @Override
    public void delete(long id) {
        wishlistRepository.deleteById(id);
    }
}
