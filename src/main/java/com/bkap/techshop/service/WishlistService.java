package com.bkap.techshop.service;

import com.bkap.techshop.dto.request.WishlistRequest;
import com.bkap.techshop.dto.response.WishlistResponse;

import java.util.List;

public interface WishlistService {

    List<WishlistResponse> findWishlistsByUserId(long userId);
    WishlistResponse save(WishlistRequest request);
    void delete(long id);
}
