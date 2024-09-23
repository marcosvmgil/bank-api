package com.example.bank_api.service;

import com.example.bank_api.repository.AccountRepository;
import com.example.bank_api.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public List<Account> getAccountsByAccountNumber(Long accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    public Account getAccountById(UUID id) {
        return accountRepository.findById(id).orElse(null);
    }

    public Account saveAccount(Account account) {
//        account.setId(UUID.randomUUID());
        return accountRepository.save(account);
    }

    public void deleteAccount(UUID id) {
        accountRepository.deleteById(id);
    }
}