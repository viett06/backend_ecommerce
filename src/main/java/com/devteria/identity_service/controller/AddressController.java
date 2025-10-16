package com.devteria.identity_service.controller;

import com.devteria.identity_service.dto.request.AddressRequest;
import com.devteria.identity_service.dto.request.AddressUpdateRequest;
import com.devteria.identity_service.dto.response.AddressResponse;
import com.devteria.identity_service.dto.response.ApiResponse;
import com.devteria.identity_service.service.impl.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private IAddressService iAddressService;
    @PostMapping
    public ApiResponse<AddressResponse> createAddress(@RequestBody AddressRequest addressRequest){
        AddressResponse addressResponse = iAddressService.createAddress(addressRequest);
        return ApiResponse.<AddressResponse>builder()
                .result(addressResponse)
                .build();
    }
    @PutMapping("/{addressId}")
    public ApiResponse<AddressResponse> updateAddress(@PathVariable Long addressId,@RequestBody AddressUpdateRequest addressUpdateRequest){
        AddressResponse addressResponse = iAddressService.updateAddress(addressId, addressUpdateRequest);
        return ApiResponse.<AddressResponse>builder()
                .result(addressResponse)
                .build();
    }
    @GetMapping
    public ApiResponse<List<AddressResponse>> getAddresses(){
        List<AddressResponse> addressResponses = iAddressService.getAddresses();
        return ApiResponse.<List<AddressResponse>>builder()
                .result(addressResponses)
                .build();
    }
    @GetMapping("/{addressId}")
    public ApiResponse<AddressResponse> getAddress(@PathVariable Long addressId){
        AddressResponse addressResponse = iAddressService.getAddress(addressId);
        return ApiResponse.<AddressResponse>builder()
                .result(addressResponse)
                .build();
    }
    @DeleteMapping("/{addressId}")
    public ApiResponse<Void> deleteAddress(@PathVariable Long addressId){
        iAddressService.deleteAddress(addressId);
        return ApiResponse.<Void>builder()
                .build();
    }


}
