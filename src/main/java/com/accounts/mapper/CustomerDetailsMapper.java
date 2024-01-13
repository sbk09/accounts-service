package com.accounts.mapper;

import com.accounts.dto.CustomerDetailsDto;
import com.accounts.entity.Accounts;
import com.accounts.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerDetailsMapper {

    CustomerDetailsMapper INSTANCE = Mappers.getMapper(CustomerDetailsMapper.class);

    CustomerDetailsDto getCustomerDetailsDto(Customer customer, Accounts accounts);
}
