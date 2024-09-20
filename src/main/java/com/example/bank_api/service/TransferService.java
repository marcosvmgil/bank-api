package com.example.bank_api.service;

import com.example.bank_api.model.Transfer;
import com.example.bank_api.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;

    public List<Transfer> getAllTransfers() {
        return transferRepository.findAll();
    }

    public Transfer getTransferById(UUID id) {
        return transferRepository.findById(id).orElse(null);
    }

    public Transfer saveTransfer(Transfer transfer) {
        // transfer.setId(UUID.randomUUID()); 
        return transferRepository.save(transfer);
    }

    public void deleteTransfer(UUID id) {
        transferRepository.deleteById(id);
    }
}