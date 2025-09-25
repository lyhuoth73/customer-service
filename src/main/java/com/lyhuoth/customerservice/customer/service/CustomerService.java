package com.lyhuoth.customerservice.customer.service;

import com.lyhuoth.customerservice.customer.dto.CreateCustomerRequestDTO;
import com.lyhuoth.customerservice.customer.dto.CustomerResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface CustomerService {

    List<CustomerResponse> getCustomerList();

    CustomerResponse createCustomer(@Valid CreateCustomerRequestDTO createCustomerRequestDTO);

}
