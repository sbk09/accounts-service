package com.accounts.repository;

import com.accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    Accounts findAccountsByAccountNumber(Long accountNumber);

    Accounts findAccountsByCustomerId(Long customerId);

    void deleteByCustomerId(Long customerId);
}
