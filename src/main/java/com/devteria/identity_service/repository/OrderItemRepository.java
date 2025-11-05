package com.devteria.identity_service.repository;

import com.devteria.identity_service.entity.OrderItem;
import com.devteria.identity_service.entity.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {

    List<OrderItem> findByOrderItemIdOrderId(Long orderId);

    List<OrderItem> findByOrderItemIdProductId(Long productId);

    @Query("SELECT oi FROM OrderItem oi WHERE oi.orderItemId.orderId = :orderId AND oi.product.productId = :productId")
    Optional<OrderItem> findByOrderIdAndProductId(@Param("orderId") Long orderId, @Param("productId") Long productId);

    boolean existsByOrderItemIdOrderIdAndOrderItemIdProductId(Long orderId, Long productId);

    @Query("SELECT SUM(oi.quantity * oi.priceEach) FROM OrderItem oi WHERE oi.orderItemId.orderId = :orderId")
    Optional<BigDecimal> getTotalAmountByOrderId(@Param("orderId") Long orderId);

    void deleteByOrderItemIdOrderId(Long orderId);
}