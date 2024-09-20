package com.example.bank_api.service;


import com.example.bank_api.model.Deposit;
import com.example.bank_api.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DepositService {

    @Autowired
    private DepositRepository depositRepository;

    public List<Deposit> getAllDeposits() {
        return depositRepository.findAll();
    }

    public Deposit getDepositById(UUID id) {
        return depositRepository.findById(id).orElse(null);
    }

    public Deposit saveDeposit(Deposit deposit) {
        // deposit.setId(UUID.randomUUID());
        return depositRepository.save(deposit);
    }

    public void deleteDeposit(UUID id) {
        depositRepository.deleteById(id);
    }
}