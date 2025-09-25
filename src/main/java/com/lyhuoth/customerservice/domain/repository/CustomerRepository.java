package com.lyhuoth.customerservice.domain.repository;

import com.lyhuoth.customerservice.domain.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
