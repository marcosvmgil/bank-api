package com.example.bank_api.controller;


import com.example.bank_api.model.Account;
import com.example.bank_api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> getAccounts(@RequestParam(required = false) Long accountNumber) {
        if (accountNumber != null) {
            List<Account> extracts = accountService.getAccountsByAccountNumber(accountNumber);
            if (extracts.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(extracts);
        } else {
            List<Account> extracts = accountService.getAllAccounts();
            return ResponseEntity.ok(extracts);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable UUID id) {
        Account account = accountService.getAccountById(id);
        return account != null ? ResponseEntity.ok(account) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.saveAccount(account);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable UUID id, @RequestBody Account accountDetails) {
        Account existingAccount = accountService.getAccountById(id);

        if (existingAccount == null) {
            return ResponseEntity.notFound().build();
        }

        existingAccount.setAmount(accountDetails.getAmount());
        existingAccount.setAccountNumber(accountDetails.getAccountNumber());

        Account updatedAccount = accountService.saveAccount(existingAccount);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable UUID id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}