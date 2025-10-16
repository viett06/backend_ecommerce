package com.devteria.identity_service.controller;

import com.devteria.identity_service.dto.request.CustomerRequest;
import com.devteria.identity_service.dto.request.CustomerUpdateRequest;
import com.devteria.identity_service.dto.response.ApiResponse;
import com.devteria.identity_service.dto.response.CustomerResponse;
import com.devteria.identity_service.service.impl.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService iCustomerService;
    @PostMapping
    public ApiResponse<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest customerRequest){
      CustomerResponse customerResponse = iCustomerService.createCustomer(customerRequest);
      return ApiResponse.<CustomerResponse>builder()
              .result(customerResponse)
              .build();
    }
    @GetMapping
    public ApiResponse<List<CustomerResponse>> getCustomers(){
        List<CustomerResponse> customerResponses = iCustomerService.getCustomers();
        return ApiResponse.<List<CustomerResponse>>builder()
                .result(customerResponses)
                .build();
   }
    @GetMapping("/myinfor")
    public ApiResponse<CustomerResponse> getMyinfor(){
       CustomerResponse customerResponse = iCustomerService.myInfor();
       return ApiResponse.<CustomerResponse>builder()
               .result(customerResponse)
               .build();
    }
    @GetMapping("/customername")
    public ApiResponse<List<CustomerResponse>> getCustomer( @RequestParam String firstName){
        List<CustomerResponse> customerResponses = iCustomerService.getCustomer(firstName);
        return ApiResponse.<List<CustomerResponse>>builder()
                .result(customerResponses)
                .build();
    }
    @DeleteMapping("/{customerId}")
    public ApiResponse<Void> deleteCustomer(@PathVariable("customerId") String customerId){
        iCustomerService.deleteCustomer(customerId);
        return ApiResponse.<Void>builder()
                .build();
    }
    @PutMapping("/{customerId}")
    public ApiResponse<CustomerResponse> updateCustomer(@PathVariable("customerId") String customerId,@RequestBody CustomerUpdateRequest customerUpdateRequest){
        CustomerResponse customerResponse = iCustomerService.updateCustomer(customerId, customerUpdateRequest);
        return ApiResponse.<CustomerResponse>builder()
                .result(customerResponse)
                .build();
    }

}
