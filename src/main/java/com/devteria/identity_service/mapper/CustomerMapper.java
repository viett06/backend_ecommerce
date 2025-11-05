package com.devteria.identity_service.mapper;

import com.devteria.identity_service.dto.request.CustomerRequest;
import com.devteria.identity_service.dto.request.CustomerUpdateRequest;
import com.devteria.identity_service.dto.response.CustomerResponse;
import com.devteria.identity_service.entity.Customer;
import com.devteria.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {CartMapper.class})
public interface CustomerMapper {
    @Mapping(target = "user",ignore = true)
    @Mapping(target = "cart",ignore = true)
    Customer toCustomer(CustomerRequest customerRequest);
    @Mapping(source = "customerId", target = "customerId") // <-- thêm dòng này
    @Mapping(source = "user", target = "userResponse")
    @Mapping(source = "cart", target = "cartResponse")
    @Mapping(source = "orders", target = "orderResponses")
    CustomerResponse toCustomerResponse(Customer customer);
    @Mapping(target = "user", ignore = true)
    void updateToCustomer(@MappingTarget Customer customer, CustomerUpdateRequest customerUpdateRequest);
}
