package com.devteria.identity_service.service.impl;

import com.devteria.identity_service.dto.request.ShipmentRequest;
import com.devteria.identity_service.dto.request.ShipmentUpdateRequest;
import com.devteria.identity_service.dto.response.ShipmentResponse;
import com.devteria.identity_service.entity.Shipment;
import com.devteria.identity_service.enums.ShipmentStatus;
import com.devteria.identity_service.exception.AppException;
import com.devteria.identity_service.exception.ErrorCode;
import com.devteria.identity_service.mapper.ShipmentMapper;
import com.devteria.identity_service.repository.ShipmentRepository;
import com.devteria.identity_service.service.ShipmentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IShipmentService implements ShipmentService {

    ShipmentRepository shipmentRepository;
    ShipmentMapper shipmentMapper;

    @Override
    @Transactional
    public ShipmentResponse createShipment(ShipmentRequest shipmentRequest) {
        // Kiểm tra tracking number đã tồn tại chưa
        if (shipmentRepository.existsByTrackingNumber(shipmentRequest.getTrackingNumber())) {
            throw new AppException(ErrorCode.SHIPMENT_TRACKING_NUMBER_EXISTS);
        }

        Shipment shipment = shipmentMapper.toShipment(shipmentRequest);

        // Nếu không có shipped date, set thành ngày hiện tại
        if (shipment.getShippedDate() == null) {
            shipment.setShippedDate(LocalDate.now());
        }

        shipment = shipmentRepository.save(shipment);
        return shipmentMapper.toShipmentResponse(shipment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ShipmentResponse> getAllShipments() {
        return shipmentRepository.findAll()
                .stream()
                .map(shipmentMapper::toShipmentResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ShipmentResponse getShipmentById(Long shipmentId) {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new AppException(ErrorCode.SHIPMENT_NOT_FOUND));
        return shipmentMapper.toShipmentResponse(shipment);
    }

    @Override
    @Transactional(readOnly = true)
    public ShipmentResponse getShipmentByTrackingNumber(String trackingNumber) {
        Shipment shipment = shipmentRepository.findByTrackingNumber(trackingNumber)
                .orElseThrow(() -> new AppException(ErrorCode.SHIPMENT_NOT_FOUND));
        return shipmentMapper.toShipmentResponse(shipment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ShipmentResponse> getShipmentsByCarrier(String carrier) {
        return shipmentRepository.findByCarrier(carrier)
                .stream()
                .map(shipmentMapper::toShipmentResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ShipmentResponse> getShipmentsByStatus(ShipmentStatus status) {
        return shipmentRepository.findByStatus(status)
                .stream()
                .map(shipmentMapper::toShipmentResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ShipmentResponse> getShipmentsByDateRange(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new AppException(ErrorCode.INVALID_DATE_RANGE);
        }

        return shipmentRepository.findByShippedDateBetween(startDate, endDate)
                .stream()
                .map(shipmentMapper::toShipmentResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ShipmentResponse> getOverdueShipments() {
        LocalDate threeDaysAgo = LocalDate.now().minusDays(3);
        List<ShipmentStatus> overdueStatuses = Arrays.asList(
                ShipmentStatus.SHIPPED,
                ShipmentStatus.IN_TRANSIT,
                ShipmentStatus.OUT_FOR_DELIVERY
        );

        return shipmentRepository.findOverdueShipments(threeDaysAgo, overdueStatuses)
                .stream()
                .map(shipmentMapper::toShipmentResponse)
                .toList();
    }

    @Override
    @Transactional
    public ShipmentResponse updateShipment(Long shipmentId, ShipmentUpdateRequest shipmentUpdateRequest) {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new AppException(ErrorCode.SHIPMENT_NOT_FOUND));

        // Kiểm tra tracking number trùng lặp (nếu có update)
        if (shipmentUpdateRequest.getTrackingNumber() != null &&
                !shipmentUpdateRequest.getTrackingNumber().equals(shipment.getTrackingNumber()) &&
                shipmentRepository.existsByTrackingNumberAndShipmentIdNot(shipmentUpdateRequest.getTrackingNumber(), shipmentId)) {
            throw new AppException(ErrorCode.SHIPMENT_TRACKING_NUMBER_EXISTS);
        }

        shipmentMapper.updateShipmentFromRequest(shipment, shipmentUpdateRequest);
        shipment = shipmentRepository.save(shipment);
        return shipmentMapper.toShipmentResponse(shipment);
    }

    @Override
    @Transactional
    public ShipmentResponse updateShipmentStatus(Long shipmentId, ShipmentStatus status) {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new AppException(ErrorCode.SHIPMENT_NOT_FOUND));

        shipment.setStatus(status);

        // Nếu status là DELIVERED và chưa có shipped date, set thành ngày hiện tại
        if (status == ShipmentStatus.DELIVERED && shipment.getShippedDate() == null) {
            shipment.setShippedDate(LocalDate.now());
        }

        shipment = shipmentRepository.save(shipment);
        return shipmentMapper.toShipmentResponse(shipment);
    }

    @Override
    @Transactional
    public void deleteShipment(Long shipmentId) {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new AppException(ErrorCode.SHIPMENT_NOT_FOUND));

        // Kiểm tra nếu shipment có orders liên kết
        if (!shipment.getOrders().isEmpty()) {
            throw new AppException(ErrorCode.SHIPMENT_HAS_ORDERS);
        }

        shipmentRepository.delete(shipment);
    }
}