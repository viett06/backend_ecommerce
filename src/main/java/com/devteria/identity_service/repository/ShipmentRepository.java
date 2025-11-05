package com.devteria.identity_service.repository;

import com.devteria.identity_service.entity.Shipment;
import com.devteria.identity_service.enums.ShipmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    Optional<Shipment> findByTrackingNumber(String trackingNumber);

    List<Shipment> findByCarrier(String carrier);

    List<Shipment> findByStatus(ShipmentStatus status);

    List<Shipment> findByShippedDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT s FROM Shipment s WHERE s.shippedDate < :date AND s.status IN :statuses")
    List<Shipment> findOverdueShipments(@Param("date") LocalDate date, @Param("statuses") List<ShipmentStatus> statuses);

    boolean existsByTrackingNumber(String trackingNumber);

    boolean existsByTrackingNumberAndShipmentIdNot(String trackingNumber, Long shipmentId);
}