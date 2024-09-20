package com.example.bank_api.controller;


import com.example.bank_api.model.Extract;
import com.example.bank_api.service.ExtractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/extract")
public class ExtractController {

    @Autowired
    private ExtractService extractService;

    @GetMapping
    public ResponseEntity<List<Extract>> getExtracts(@RequestParam(required = false) Long accountNumber) {
        if (accountNumber != null) {
            List<Extract> extracts = extractService.getExtractsByAccountNumber(accountNumber);
            if (extracts.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(extracts);
        } else {
            List<Extract> extracts = extractService.getAllExtracts();
            return ResponseEntity.ok(extracts);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Extract> getExtractById(@PathVariable UUID id) {
        Extract extract = extractService.getExtractById(id);
        return extract != null ? ResponseEntity.ok(extract) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Extract createExtract(@RequestBody Extract extract) {
        return extractService.saveExtract(extract);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExtract(@PathVariable UUID id) {
        extractService.deleteExtract(id);
        return ResponseEntity.noContent().build();
    }
}