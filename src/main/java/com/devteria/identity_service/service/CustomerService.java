package com.devteria.identity_service.service;

import com.devteria.identity_service.dto.request.CustomerRequest;
import com.devteria.identity_service.dto.request.CustomerUpdateRequest;
import com.devteria.identity_service.dto.response.CustomerResponse;

import java.util.List;
import java.util.Set;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest customerRequest);
    List<CustomerResponse> getCustomers();
    List<CustomerResponse> getCustomer(String username);
    CustomerResponse myInfor();
    void deleteCustomer(String customerId);
    CustomerResponse updateCustomer(String customerId, CustomerUpdateRequest customerUpdateRequest);



}
