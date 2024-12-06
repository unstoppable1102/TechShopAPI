package com.bkap.techshop.service.impl;

import com.bkap.techshop.dto.request.WishlistRequest;
import com.bkap.techshop.dto.response.WishlistResponse;
import com.bkap.techshop.entity.Product;
import com.bkap.techshop.entity.User;
import com.bkap.techshop.entity.Wishlist;
import com.bkap.techshop.exception.AppException;
import com.bkap.techshop.exception.ErrorCode;
import com.bkap.techshop.repository.ProductRepository;
import com.bkap.techshop.repository.UserRepository;
import com.bkap.techshop.repository.WishlistRepository;
import com.bkap.techshop.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public List<WishlistResponse> findWishlistsByUserId(long userId) {
        List<Wishlist> wishlists = wishlistRepository.findWishlistsByUserId(userId);

        return wishlists.stream()
                .map((element) -> modelMapper.map(element, WishlistResponse.class))
                .collect(Collectors.toList());
    }


    @Override
    public WishlistResponse save(WishlistRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));


        Wishlist exists = wishlistRepository.findByUserIdAndProductId(request.getUserId(), product.getId());
        if (exists != null) {
            return modelMapper.map(exists, WishlistResponse.class);

        }else {
            Wishlist wishlist = new Wishlist();
            wishlist.setUser(user);
            wishlist.setProduct(product);
            wishlist.setPrice(product.getPrice());
            return modelMapper.map(wishlistRepository.save(wishlist), WishlistResponse.class);
        }

    }

    @Override
    public void delete(long id) {
        wishlistRepository.deleteById(id);
    }
}
