package com.accounts.service.impl;

import com.accounts.dto.AccountsDto;
import com.accounts.entity.Accounts;
import com.accounts.mapper.AccountsMapper;
import com.accounts.repository.AccountsRepository;
import com.accounts.repository.CustomerRepository;
import com.accounts.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl implements AccountsService {

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountsMapper accountsMapper;

    /**
     * @param accountsDto
     */
    @Override
    public void createAccount(AccountsDto accountsDto) {
        Accounts accounts = accountsMapper.convertAccountsDtoToEntity(accountsDto);
        accountsRepository.save(accounts);
    }
}
