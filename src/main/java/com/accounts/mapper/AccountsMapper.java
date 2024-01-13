package com.accounts.mapper;

import com.accounts.dto.AccountsDto;
import com.accounts.dto.CustomerDetailsDto;
import com.accounts.entity.Accounts;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountsMapper {

    AccountsMapper INSTANCE = Mappers.getMapper(AccountsMapper.class);

    AccountsDto convertAccountToDto(Accounts accounts);

    Accounts convertAccountsDtoToEntity(AccountsDto accountsDto);

    Accounts getAccountFromCustomerDetailsDto(CustomerDetailsDto customerDetailsDto);
}
