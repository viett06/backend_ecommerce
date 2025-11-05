package com.devteria.identity_service.service;

import com.devteria.identity_service.dto.request.ShipmentRequest;
import com.devteria.identity_service.dto.request.ShipmentUpdateRequest;
import com.devteria.identity_service.dto.response.ShipmentResponse;
import com.devteria.identity_service.enums.ShipmentStatus;

import java.time.LocalDate;
import java.util.List;

public interface ShipmentService {
    ShipmentResponse createShipment(ShipmentRequest shipmentRequest);
    List<ShipmentResponse> getAllShipments();
    ShipmentResponse getShipmentById(Long shipmentId);
    ShipmentResponse getShipmentByTrackingNumber(String trackingNumber);
    List<ShipmentResponse> getShipmentsByCarrier(String carrier);
    List<ShipmentResponse> getShipmentsByStatus(ShipmentStatus status);
    List<ShipmentResponse> getShipmentsByDateRange(LocalDate startDate, LocalDate endDate);
    List<ShipmentResponse> getOverdueShipments();
    ShipmentResponse updateShipment(Long shipmentId, ShipmentUpdateRequest shipmentUpdateRequest);
    ShipmentResponse updateShipmentStatus(Long shipmentId, ShipmentStatus status);
    void deleteShipment(Long shipmentId);
}