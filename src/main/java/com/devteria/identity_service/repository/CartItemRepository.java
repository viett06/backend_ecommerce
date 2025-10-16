package com.devteria.identity_service.repository;

import com.devteria.identity_service.entity.Cart;
import com.devteria.identity_service.entity.CartItem;
import com.devteria.identity_service.entity.CartItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, CartItemId> {
    List<CartItem> findByCart_CartId(Long cartId);
    void deleteByCart_CartId(Long cartId);
    void deleteByCart(Cart cart);
    @Query("""
        SELECT ci 
        FROM CartItem ci 
        JOIN ci.cart c 
        JOIN c.customer cu 
        JOIN cu.user u 
        WHERE u.name = :username AND ci.product.productId = :productId
    """)
    Optional<CartItem> findByUsernameAndProductId(@Param("username") String username,
                                                  @Param("productId") Long productId);
}
