package com.example.bank_api.repository;

import com.example.bank_api.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, UUID> {
    Transfer findByAccountNumber(Long accountNumber);
}