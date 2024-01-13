package com.accounts.service;

import com.accounts.dto.AccountsDto;
import org.springframework.stereotype.Service;

@Service
public interface AccountsService {

    void createAccount(AccountsDto accountsDto);
}
