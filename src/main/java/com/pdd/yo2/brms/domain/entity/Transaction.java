package com.pdd.yo2.brms.domain.entity;

import com.pdd.yo2.brms.sharedkernel.constant.TransactionStatus;
import com.pdd.yo2.brms.sharedkernel.constant.TransactionType;

import java.time.LocalDate;

public abstract class Transaction {
    String transactionId;
    TransactionType transactionType;
    LocalDate transactionDate;
    TransactionStatus transactionStatus;
    String statusMessage;

    public Transaction() {
        this.transactionDate = LocalDate.now();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public abstract void setTransactionId(String transactionId);

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType type) {
        transactionType = type;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public abstract void setTransactionStatus(TransactionStatus transactionStatus);
    public abstract void setStatusMessage(String statusMessage);
    public abstract boolean isValidTransaction();
}
