package com.pdd.yo2.brms.domain.entity;

import com.pdd.yo2.brms.sharedkernel.constant.TransactionStatus;
import com.pdd.yo2.brms.sharedkernel.constant.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Remittance extends Transaction {

    private String customerId;
    private String customerName;

    // ISO-3166 Country Code
    private String countryOfOrigin;

    private String beneficiaryId;
    private String beneficiaryName;

    // ISO-3166 Country Code
    private String beneficiaryCountry;

    private BigDecimal transactionAmount;

    // ISO-4217 Currency Code
    private String currencyCode;

    private Remittance() {
        this.transactionType = TransactionType.REMITTANCE;
    }

    @Override
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public void setTransactionStatus(TransactionStatus transactionStatus) { this.transactionStatus = transactionStatus; }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public String getBeneficiaryCountry() {
        return beneficiaryCountry;
    }

    public BigDecimal getTransactionAmount() { return transactionAmount; }

    public String getCurrencyCode() { return currencyCode; }

    public String getStatusMessage() { return statusMessage; }

    @Override
    public void setStatusMessage(String statusMessage) { this.statusMessage = statusMessage; }

    @Override
    public boolean isValidTransaction() {
        return this.transactionStatus != TransactionStatus.REJECTED;
    }

    @Override
    public String toString() {
        return String.format("remittance[id=%s, customerName=%s, origin=%s, beneficiaryName=%s, destCountry=%s, amount=%.2f]",
                transactionId, customerName, countryOfOrigin, beneficiaryName, beneficiaryCountry, transactionAmount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Remittance)) return false;
        Remittance remittance = (Remittance) o;
        return Objects.equals(remittance.transactionId, transactionId) &&
                remittance.transactionType == transactionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, transactionType, customerId, customerName, countryOfOrigin,
                beneficiaryId, beneficiaryName, beneficiaryCountry, transactionAmount, transactionDate);
    }

    public static RemittanceBuilder newBuilder() { return new RemittanceBuilder(); }

    public static final class RemittanceBuilder {
        private String id;
        private String customerId;
        private String customerName;
        private String origin;
        private String beneficiaryId;
        private String beneficiaryName;
        private String beneficiaryCountry;
        private BigDecimal amount;
        private String currency;
        private LocalDate date;

        private RemittanceBuilder() {}

        public RemittanceBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public RemittanceBuilder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public RemittanceBuilder withCustomerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public RemittanceBuilder withOrigin(String origin) {
            this.origin = origin;
            return this;
        }

        public RemittanceBuilder withBeneficiaryId(String beneficiaryId) {
            this.beneficiaryId = beneficiaryId;
            return this;
        }

        public RemittanceBuilder withBeneficiaryName(String beneficiaryName) {
            this.beneficiaryName = beneficiaryName;
            return this;
        }

        public RemittanceBuilder withBeneficiaryCountry(String beneficiaryCountry) {
            this.beneficiaryCountry = beneficiaryCountry;
            return this;
        }

        public RemittanceBuilder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public RemittanceBuilder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public RemittanceBuilder withDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Remittance build() {
            var remittance = new Remittance();
            remittance.transactionId = id;
            remittance.customerId = customerId;
            remittance.customerName = customerName;
            remittance.countryOfOrigin = origin;
            remittance.beneficiaryId = beneficiaryId;
            remittance.beneficiaryName = beneficiaryName;
            remittance.beneficiaryCountry = beneficiaryCountry;
            remittance.transactionAmount = amount;
            remittance.currencyCode = currency;
            remittance.transactionDate = date;
            return remittance;
        }
    }
}