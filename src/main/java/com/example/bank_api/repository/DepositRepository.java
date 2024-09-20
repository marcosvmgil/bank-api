package com.example.bank_api.repository;

import com.example.bank_api.model.Account;
import com.example.bank_api.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, UUID> {
    Deposit findByAccountNumber(Long accountNumber);
}
