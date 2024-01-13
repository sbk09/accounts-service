package com.accounts.mapper;

import com.accounts.dto.CustomerDetailsDto;
import com.accounts.dto.CustomerDto;
import com.accounts.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDto getCustomerDtoFromEntity(Customer customer);

    Customer getCustomerFromDto(CustomerDto customerDto);

    Customer getCustomerFromCustomerDetails(CustomerDetailsDto customerDetailsDto);
}
