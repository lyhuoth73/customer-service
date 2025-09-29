package com.lyhuoth.customerservice.feature.customer.mapper;

import com.lyhuoth.customerservice.feature.customer.dto.CreateCustomerRequestDTO;
import com.lyhuoth.customerservice.feature.customer.dto.CustomerResponse;
import com.lyhuoth.customerservice.domain.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CustomerMapper {
//    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    List<CustomerResponse> fromListEntityToListResponse(List<CustomerEntity> customerEntities);

    CustomerEntity dtoToEntity(CreateCustomerRequestDTO createCustomerRequestDTO);

    CustomerResponse fromEntityToResponse(CustomerEntity customerEntity);
}
