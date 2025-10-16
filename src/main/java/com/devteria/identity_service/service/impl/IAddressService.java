package com.devteria.identity_service.service.impl;

import com.devteria.identity_service.dto.request.AddressRequest;
import com.devteria.identity_service.dto.request.AddressUpdateRequest;
import com.devteria.identity_service.dto.response.AddressResponse;
import com.devteria.identity_service.entity.Address;
import com.devteria.identity_service.entity.Customer;
import com.devteria.identity_service.entity.User;
import com.devteria.identity_service.exception.AppException;
import com.devteria.identity_service.exception.ErrorCode;
import com.devteria.identity_service.mapper.AddressMapper;
import com.devteria.identity_service.repository.AddressRepository;
import com.devteria.identity_service.repository.CustomerRepository;
import com.devteria.identity_service.repository.UserRepository;
import com.devteria.identity_service.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class IAddressService implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    @PreAuthorize("hasAuthority('VIEW')")
    public AddressResponse createAddress(AddressRequest addressRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByName(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Customer customer = customerRepository.findByUser(user)
                .orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_EXISTED));
        Address address = addressMapper.toAddress(addressRequest);
        address.setCustomer(customer);
        addressRepository.save(address);
        return addressMapper.toAddressResponse(address);
    }

    @Override
    @PreAuthorize("hasAuthority('VIEW')")
    public AddressResponse updateAddress(Long addressId, AddressUpdateRequest addressUpdateRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByName(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Customer customer = customerRepository.findByUser(user)
                .orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_EXISTED));
        Address address = addressRepository.findByIdAndUserName(addressId, username)
                .orElseThrow(() -> new AppException(ErrorCode.ADDRESS_NOT_EXISTED));
        addressMapper.updateAddressToAddress(address, addressUpdateRequest);
        address.setCustomer(customer);
        addressRepository.save(address);
        return addressMapper.toAddressResponse(address);
    }

    @Override
    @PreAuthorize("hasAuthority('VIEW')")
    public List<AddressResponse> getAddresses() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Address> addresses = addressRepository.findAllByUserName(username);
        return addresses.stream()
                .map(addressMapper::toAddressResponse)
                .toList();
    }

    @Override
    @PreAuthorize("hasAuthority('VIEW')")
    public AddressResponse getAddress(Long addressId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Address address = addressRepository.findByIdAndUserName(addressId, username)
                .orElseThrow(() -> new AppException(ErrorCode.ADDRESS_NOT_EXISTED));
        return addressMapper.toAddressResponse(address);
    }

    @Override
    @PreAuthorize("hasAuthority('VIEW')")
    public void deleteAddress(Long addressId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Address address = addressRepository.findByIdAndUserName(addressId, username)
                .orElseThrow(() -> new AppException(ErrorCode.ADDRESS_NOT_EXISTED));
        addressRepository.delete(address);
    }
}
