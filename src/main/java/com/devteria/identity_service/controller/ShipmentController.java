package com.devteria.identity_service.controller;

import com.devteria.identity_service.dto.request.ShipmentRequest;
import com.devteria.identity_service.dto.request.ShipmentUpdateRequest;
import com.devteria.identity_service.dto.response.ApiResponse;
import com.devteria.identity_service.dto.response.ShipmentResponse;
import com.devteria.identity_service.enums.ShipmentStatus;
import com.devteria.identity_service.service.ShipmentService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/shipments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShipmentController {

    ShipmentService shipmentService;

    @PostMapping
    public ApiResponse<ShipmentResponse> createShipment(@Valid @RequestBody ShipmentRequest shipmentRequest) {
        ShipmentResponse shipmentResponse = shipmentService.createShipment(shipmentRequest);
        return ApiResponse.<ShipmentResponse>builder()
                .result(shipmentResponse)
                .build();
    }

    @GetMapping
    public ApiResponse<List<ShipmentResponse>> getAllShipments() {
        List<ShipmentResponse> shipmentResponses = shipmentService.getAllShipments();
        return ApiResponse.<List<ShipmentResponse>>builder()
                .result(shipmentResponses)
                .build();
    }

    @GetMapping("/{shipmentId}")
    public ApiResponse<ShipmentResponse> getShipmentById(@PathVariable("shipmentId") Long shipmentId) {
        ShipmentResponse shipmentResponse = shipmentService.getShipmentById(shipmentId);
        return ApiResponse.<ShipmentResponse>builder()
                .result(shipmentResponse)
                .build();
    }

    @GetMapping("/tracking/{trackingNumber}")
    public ApiResponse<ShipmentResponse> getShipmentByTrackingNumber(@PathVariable("trackingNumber") String trackingNumber) {
        ShipmentResponse shipmentResponse = shipmentService.getShipmentByTrackingNumber(trackingNumber);
        return ApiResponse.<ShipmentResponse>builder()
                .result(shipmentResponse)
                .build();
    }

    @GetMapping("/carrier/{carrier}")
    public ApiResponse<List<ShipmentResponse>> getShipmentsByCarrier(@PathVariable("carrier") String carrier) {
        List<ShipmentResponse> shipmentResponses = shipmentService.getShipmentsByCarrier(carrier);
        return ApiResponse.<List<ShipmentResponse>>builder()
                .result(shipmentResponses)
                .build();
    }

    @GetMapping("/status/{status}")
    public ApiResponse<List<ShipmentResponse>> getShipmentsByStatus(@PathVariable("status") ShipmentStatus status) {
        List<ShipmentResponse> shipmentResponses = shipmentService.getShipmentsByStatus(status);
        return ApiResponse.<List<ShipmentResponse>>builder()
                .result(shipmentResponses)
                .build();
    }

    @GetMapping("/date-range")
    public ApiResponse<List<ShipmentResponse>> getShipmentsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<ShipmentResponse> shipmentResponses = shipmentService.getShipmentsByDateRange(startDate, endDate);
        return ApiResponse.<List<ShipmentResponse>>builder()
                .result(shipmentResponses)
                .build();
    }

    @GetMapping("/overdue")
    public ApiResponse<List<ShipmentResponse>> getOverdueShipments() {
        List<ShipmentResponse> shipmentResponses = shipmentService.getOverdueShipments();
        return ApiResponse.<List<ShipmentResponse>>builder()
                .result(shipmentResponses)
                .build();
    }

    @PutMapping("/{shipmentId}")
    public ApiResponse<ShipmentResponse> updateShipment(
            @PathVariable("shipmentId") Long shipmentId,
            @Valid @RequestBody ShipmentUpdateRequest shipmentUpdateRequest) {
        ShipmentResponse shipmentResponse = shipmentService.updateShipment(shipmentId, shipmentUpdateRequest);
        return ApiResponse.<ShipmentResponse>builder()
                .result(shipmentResponse)
                .build();
    }

    @PutMapping("/{shipmentId}/status")
    public ApiResponse<ShipmentResponse> updateShipmentStatus(
            @PathVariable("shipmentId") Long shipmentId,
            @RequestParam ShipmentStatus status) {
        ShipmentResponse shipmentResponse = shipmentService.updateShipmentStatus(shipmentId, status);
        return ApiResponse.<ShipmentResponse>builder()
                .result(shipmentResponse)
                .build();
    }

    @DeleteMapping("/{shipmentId}")
    public ApiResponse<Void> deleteShipment(@PathVariable("shipmentId") Long shipmentId) {
        shipmentService.deleteShipment(shipmentId);
        return ApiResponse.<Void>builder()
                .build();
    }
}