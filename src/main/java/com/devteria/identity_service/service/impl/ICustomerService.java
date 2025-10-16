package com.devteria.identity_service.service.impl;

import com.devteria.identity_service.dto.request.CustomerRequest;
import com.devteria.identity_service.dto.request.CustomerUpdateRequest;
import com.devteria.identity_service.dto.response.CustomerResponse;
import com.devteria.identity_service.entity.Cart;
import com.devteria.identity_service.entity.Customer;
import com.devteria.identity_service.entity.User;
import com.devteria.identity_service.exception.AppException;
import com.devteria.identity_service.exception.ErrorCode;
import com.devteria.identity_service.mapper.CustomerMapper;
import com.devteria.identity_service.repository.CustomerRepository;
import com.devteria.identity_service.repository.UserRepository;
import com.devteria.identity_service.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ICustomerService implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;



    @Override
    @Transactional
    @PreAuthorize("hasRole('USER')")
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer customer = customerMapper.toCustomer(customerRequest);
//        log.info(">>> username request: " + customerRequest.getUsername());
//        User user = userRepository.findByName(customerRequest.getUsername())
//
//                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
//        customer.setUser(user);
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByName(userName).orElseThrow(()->new AppException(ErrorCode.USER_NOT_EXISTED));
        if (customerRepository.findByUser(user).isPresent()) {
            throw new AppException(ErrorCode.CUSTOMER_EXISTED);
        }
        customer.setUser(user);
        Cart cart = new Cart();
        cart.setCustomer(customer);
        customer.setCart(cart);
        // cascade = ALL, khi lưu customer thì cart cũng tự lưu
        customerRepository.save(customer);
        return customerMapper.toCustomerResponse(customer);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<CustomerResponse> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map((customer)-> customerMapper.toCustomerResponse(customer)).toList();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<CustomerResponse> getCustomer(String firstName) {
        List<Customer> customers = customerRepository.findByFirstName(firstName);
        if (customers.isEmpty()) {
            throw new AppException(ErrorCode.CUSTOMER_NOT_EXISTED);
        }
        return customers.stream().map((customer) -> customerMapper.toCustomerResponse(customer)).toList();
    }

    @Override
    @PreAuthorize("hasAuthority('VIEW')")
    public CustomerResponse myInfor() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByName(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Customer customer = customerRepository.findByUser(user)
                .orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_EXISTED));
        return customerMapper.toCustomerResponse(customer);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('VIEW')")
    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('VIEW')")
    public CustomerResponse updateCustomer(String customerId, CustomerUpdateRequest customerUpdateRequest) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new AppException(ErrorCode.CUSTOMER_NOT_EXISTED));
      customerMapper.updateToCustomer(customer, customerUpdateRequest);
      customerRepository.save(customer);
      return customerMapper.toCustomerResponse(customer);
    }
}
