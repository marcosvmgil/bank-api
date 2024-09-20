package com.example.bank_api.service;


import com.example.bank_api.model.Extract;
import com.example.bank_api.repository.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExtractService {

    @Autowired
    private ExtractRepository extractRepository;

    public List<Extract> getExtractsByAccountNumber(Long accountNumber) {
        return extractRepository.findByAccountNumber(accountNumber);
    }

    public List<Extract> getAllExtracts() {
        return extractRepository.findAll();
    }

    public Extract getExtractById(UUID id) {
        return extractRepository.findById(id).orElse(null);
    }

    public Extract saveExtract(Extract extract) {
//        extract.setId(UUID.randomUUID());
        return extractRepository.save(extract);
    }

    public void deleteExtract(UUID id) {
        extractRepository.deleteById(id);
    }
}