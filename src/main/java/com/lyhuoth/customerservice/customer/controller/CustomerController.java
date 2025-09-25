package com.lyhuoth.customerservice.customer.controller;

import com.lyhuoth.customerservice.customer.dto.CreateCustomerRequestDTO;
import com.lyhuoth.customerservice.customer.dto.CustomerResponse;
import com.lyhuoth.customerservice.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/public")
    public List<CustomerResponse> getCustomerPublic() {

       return customerService.getCustomerList();
    }

    @PostMapping
    public CustomerResponse createCustomer(@Valid @RequestBody CreateCustomerRequestDTO createCustomerRequestDTO){

        return customerService.createCustomer(createCustomerRequestDTO);
    }

    @GetMapping("private")
    public CustomerResponse getCustomerPrivate() {
        return CustomerResponse
                .builder()
                .customerNumber("002")
                .firstname("Kov")
                .lastName("lyhuoth")
                .email("kov@gmail.com")
                .build();
    }
}
