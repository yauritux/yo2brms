package com.pdd.yo2.brms.domain.usecase.dto.input;

import com.pdd.yo2.brms.sharedkernel.constant.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class RemittanceRequest {
    private String transactionId;
    private final TransactionType transactionType = TransactionType.REMITTANCE;
    private String customerId;
    private String customerName;

    // ISO-3166 Country Code
    private String countryOfOrigin;

    private String beneficiaryId;
    private String beneficiaryName;

    // ISO-3166 Country Code
    private String beneficiaryCountry;

    private BigDecimal amount;

    // ISO-4217 Currency Code
    private String currencyCode;

    private LocalDate transactionDate;

    private RemittanceRequest() {}

    public String getTransactionId() {
        return transactionId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public String getBeneficiaryId() {
        return beneficiaryId;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public String getBeneficiaryCountry() {
        return beneficiaryCountry;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrencyCode() { return currencyCode; }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public static RemittanceRequestBuilder newBuilder() { return new RemittanceRequestBuilder(); }

    public static final class RemittanceRequestBuilder {
        private String transactionId;
        private String customerId;
        private String customerName;
        private String origin;
        private String beneficiaryId;
        private String beneficiaryName;
        private String beneficiaryCountry;
        private BigDecimal amount;
        private String currency;
        private LocalDate transactionDate;

        private RemittanceRequestBuilder() {}

        public RemittanceRequestBuilder withTransactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public RemittanceRequestBuilder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public RemittanceRequestBuilder withCustomerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public RemittanceRequestBuilder withOrigin(String origin) {
            this.origin = origin;
            return this;
        }

        public RemittanceRequestBuilder withBeneficiaryId(String beneficiaryId) {
            this.beneficiaryId = beneficiaryId;
            return this;
        }

        public RemittanceRequestBuilder withBeneficiaryName(String beneficiaryName) {
            this.beneficiaryName = beneficiaryName;
            return this;
        }

        public RemittanceRequestBuilder withBeneficiaryCountry(String beneficiaryCountry) {
            this.beneficiaryCountry = beneficiaryCountry;
            return this;
        }

        public RemittanceRequestBuilder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public RemittanceRequestBuilder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public RemittanceRequestBuilder withTransactionDate(LocalDate transactionDate) {
            this.transactionDate = transactionDate;
            return this;
        }

        public RemittanceRequest build() {
            var request = new RemittanceRequest();
            request.transactionId = transactionId;
            request.customerId = customerId;
            request.customerName = customerName;
            request.countryOfOrigin = origin;
            request.beneficiaryId = beneficiaryId;
            request.beneficiaryName = beneficiaryName;
            request.beneficiaryCountry = beneficiaryCountry;
            request.amount = amount;
            request.currencyCode = currency;
            request.transactionDate = transactionDate;
            return request;
        }
    }

    @Override
    public String toString() {
        return "RemittanceRequest{" +
                "transactionId=" + transactionId +
                "transactionType=" + transactionType +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", countryOfOrigin='" + countryOfOrigin + '\'' +
                ", beneficiaryId='" + beneficiaryId + '\'' +
                ", beneficiaryName='" + beneficiaryName + '\'' +
                ", beneficiaryCountry='" + beneficiaryCountry + '\'' +
                ", amount=" + amount +
                ", currency='" + currencyCode + '\'' +
                ", transactionDate='" + transactionDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RemittanceRequest that = (RemittanceRequest) o;
        return transactionId.equals(that.transactionId) && transactionType == that.transactionType &&
                customerId.equals(that.customerId) &&
                customerName.equals(that.customerName) && countryOfOrigin.equals(that.countryOfOrigin)
                && beneficiaryId.equals(that.beneficiaryId) && beneficiaryName.equals(that.beneficiaryName)
                && beneficiaryCountry.equals(that.beneficiaryCountry) && amount.equals(that.amount)
                && currencyCode.equals(that.currencyCode)
                && transactionDate.equals(that.transactionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                transactionId, transactionType, customerId, customerName,
                countryOfOrigin, beneficiaryId, beneficiaryName,
                beneficiaryCountry, amount, currencyCode, transactionDate);
    }
}