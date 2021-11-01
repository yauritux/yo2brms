package com.pdd.yo2.brms.domain.usecase.dto.output;

import com.pdd.yo2.brms.sharedkernel.constant.TransactionStatus;
import com.pdd.yo2.brms.sharedkernel.constant.TransactionType;

import java.io.Serializable;
import java.util.Objects;

public class RemittanceResponse implements Serializable {

    private final String transactionId;
    private final TransactionType transactionType = TransactionType.REMITTANCE;
    private final TransactionStatus transactionStatus;

    public RemittanceResponse(String transactionId, TransactionStatus transactionStatus) {
        this.transactionId = transactionId;
        this.transactionStatus = getTransactionStatus();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    @Override
    public String toString() {
        return "RemittanceResponse{" +
                "transactionId='" + transactionId + '\'' +
                ", transactionType=" + transactionType +
                ", transactionStatus=" + transactionStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RemittanceResponse)) return false;
        var response = (RemittanceResponse) o;
        return Objects.equals(response.transactionId, transactionId) &&
                response.transactionType == transactionType &&
                response.transactionStatus == transactionStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, transactionType, transactionStatus);
    }
}