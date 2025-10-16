package com.devteria.identity_service.mapper;

import com.devteria.identity_service.dto.request.AddressRequest;
import com.devteria.identity_service.dto.request.AddressUpdateRequest;
import com.devteria.identity_service.dto.response.AddressResponse;
import com.devteria.identity_service.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mapping(target = "customer",ignore = true)
    Address toAddress(AddressRequest addressRequest);
    @Mapping(source = "addressId", target = "addressId")
    AddressResponse toAddressResponse(Address address);
    @Mapping(target = "customer",ignore = true)
    Address updateAddressToAddress(@MappingTarget Address address, AddressUpdateRequest addressUpdateRequest);
}
