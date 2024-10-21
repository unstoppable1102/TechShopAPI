package com.bkap.techshop.repository;

import com.bkap.techshop.entity.Cart;
import com.bkap.techshop.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCart(Cart cart);

    @Query("select count(ci) from CartItem ci where ci.cart.user.id = :userId")
    long countCartItemsByUserId(long userId);

    CartItem findByCartIdAndProductId(long cartId, long productId);

    List<CartItem> findByCartId(long cartId);
}