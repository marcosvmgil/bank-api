package com.example.bank_api.model;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "deposit")
public class Deposit {

    @Id
    private UUID id;

    @Column(name = "account_number", nullable = false)
    private Long accountNumber;

    @Column(name = "amount_total", nullable = false)
    private BigDecimal amountTotal;

    @Column(name = "amount_operation", nullable = false)
    private BigDecimal amountOperation;

    @Column(name = "date", nullable = false)
    private Timestamp date;

    @Column(name = "operation", nullable = false)
    private String operation;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public BigDecimal getAmountOperation() {
        return amountOperation;
    }

    public void setAmountOperation(BigDecimal amountOperation) {
        this.amountOperation = amountOperation;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}