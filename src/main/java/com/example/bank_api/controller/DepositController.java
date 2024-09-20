package com.example.bank_api.controller;


import com.example.bank_api.model.Deposit;
import com.example.bank_api.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/deposit")
public class DepositController {

    @Autowired
    private DepositService depositService;

    @GetMapping
    public List<Deposit> getAllDeposits() {
        return depositService.getAllDeposits();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deposit> getDepositById(@PathVariable UUID id) {
        Deposit deposit = depositService.getDepositById(id);
        return deposit != null ? ResponseEntity.ok(deposit) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Deposit createDeposit(@RequestBody Deposit deposit) {
        return depositService.saveDeposit(deposit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeposit(@PathVariable UUID id) {
        depositService.deleteDeposit(id);
        return ResponseEntity.noContent().build();
    }
}