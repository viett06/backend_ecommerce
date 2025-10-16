package com.devteria.identity_service.service;

import com.devteria.identity_service.dto.request.AddressRequest;
import com.devteria.identity_service.dto.request.AddressUpdateRequest;
import com.devteria.identity_service.dto.response.AddressResponse;

import java.util.List;

public interface AddressService {
    AddressResponse createAddress(AddressRequest addressRequest);
    AddressResponse updateAddress(Long addressId, AddressUpdateRequest addressUpdateRequest);
    List<AddressResponse> getAddresses();
    AddressResponse getAddress(Long addressId);
    void deleteAddress(Long addressId);

}
