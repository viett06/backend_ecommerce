package com.devteria.identity_service.repository;

import com.devteria.identity_service.entity.InventoryProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryProfileRepository extends JpaRepository<InventoryProfile, Long> {
    @Query("SELECT i FROM InventoryProfile i WHERE i.product.id = :productId")
    Optional<InventoryProfile> findByProductId(@Param("productId") Long productId);
}
