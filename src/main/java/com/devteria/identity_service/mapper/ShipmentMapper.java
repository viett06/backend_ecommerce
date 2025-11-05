package com.devteria.identity_service.mapper;

import com.devteria.identity_service.dto.request.ShipmentRequest;
import com.devteria.identity_service.dto.request.ShipmentUpdateRequest;
import com.devteria.identity_service.dto.response.ShipmentResponse;
import com.devteria.identity_service.entity.Shipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ShipmentMapper {

    @Mapping(target = "shipmentId", ignore = true)
    @Mapping(target = "orders", ignore = true)
    Shipment toShipment(ShipmentRequest shipmentRequest);

    @Mapping(target = "orderIds", expression = "java(shipment.getOrders().stream().map(order -> order.getOrderId()).collect(java.util.stream.Collectors.toList()))")
    @Mapping(target = "orderCount", expression = "java(shipment.getOrders().size())")
    ShipmentResponse toShipmentResponse(Shipment shipment);

    @Mapping(target = "shipmentId", ignore = true)
    @Mapping(target = "orders", ignore = true)
    void updateShipmentFromRequest(@MappingTarget Shipment shipment, ShipmentUpdateRequest shipmentUpdateRequest);
}