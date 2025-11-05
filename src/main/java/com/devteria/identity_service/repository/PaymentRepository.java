package com.devteria.identity_service.repository;

import com.devteria.identity_service.entity.Payment;
import com.devteria.identity_service.enums.PaymentMethod;
import com.devteria.identity_service.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByTransactionId(String transactionId);
    List<Payment> findByPaymentMethod(PaymentMethod method);
    List<Payment> findByStatus(PaymentStatus status);
    List<Payment> findByPaymentDateBetween(LocalDateTime start, LocalDateTime end);
    boolean existsByTransactionId(String transactionId);

    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.status = 'COMPLETED' AND p.paymentDate BETWEEN :start AND :end")
    Optional<BigDecimal> getTotalRevenue(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}