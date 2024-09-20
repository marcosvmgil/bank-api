package com.example.bank_api.repository;

import com.example.bank_api.model.Deposit;
import com.example.bank_api.model.Extract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExtractRepository extends JpaRepository<Extract, UUID> {
    List<Extract> findByAccountNumber(Long accountNumber);

}