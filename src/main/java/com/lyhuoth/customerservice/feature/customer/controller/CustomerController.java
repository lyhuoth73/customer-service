package com.lyhuoth.customerservice.feature.customer.controller;

import com.lyhuoth.core.exception.BusinessException;
import com.lyhuoth.customerservice.feature.customer.dto.CreateCustomerRequestDTO;
import com.lyhuoth.customerservice.feature.customer.dto.CustomerResponse;
import com.lyhuoth.customerservice.feature.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/public")
    public List<CustomerResponse> getCustomerPublic() throws BusinessException {
        if (true){
            throw new BusinessException("-200", "Hello");
        }

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
