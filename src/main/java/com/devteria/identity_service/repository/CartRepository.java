package com.devteria.identity_service.repository;

import com.devteria.identity_service.entity.Cart;
import com.devteria.identity_service.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    @Query("SELECT c FROM Cart c WHERE c.customer = :customer")
    Optional<Cart> findByCustomer(@Param("customer") Customer customer);
}
