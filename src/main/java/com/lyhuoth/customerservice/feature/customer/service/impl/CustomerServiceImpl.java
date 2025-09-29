package com.lyhuoth.customerservice.feature.customer.service.impl;

import com.lyhuoth.customerservice.feature.customer.dto.CreateCustomerRequestDTO;
import com.lyhuoth.customerservice.feature.customer.dto.CustomerResponse;
import com.lyhuoth.customerservice.feature.customer.mapper.CustomerMapper;
import com.lyhuoth.customerservice.feature.customer.service.CustomerService;
import com.lyhuoth.customerservice.domain.entity.CustomerEntity;
import com.lyhuoth.customerservice.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerResponse> getCustomerList() {
        List<CustomerEntity> customerEntities = customerRepository.findAll();
        List<CustomerResponse>customerResponses = customerMapper.fromListEntityToListResponse(customerEntities);
        return customerResponses;
    }

    @Override
    public CustomerResponse createCustomer(CreateCustomerRequestDTO createCustomerRequestDTO) {
        CustomerEntity customerEntity = customerMapper.dtoToEntity(createCustomerRequestDTO);
        customerEntity.setCustomerNumber(UUID.randomUUID().toString());
        customerEntity.setCreateAt(LocalDateTime.now());
        customerEntity.setUpdateAt(LocalDateTime.now());
        customerEntity = customerRepository.save(customerEntity);
        CustomerResponse customerResponse = customerMapper.fromEntityToResponse(customerEntity);
        return customerResponse;
    }

}
