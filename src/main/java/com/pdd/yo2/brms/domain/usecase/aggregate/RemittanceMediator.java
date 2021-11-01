package com.pdd.yo2.brms.domain.usecase.aggregate;

import com.pdd.yo2.brms.domain.entity.Remittance;
import com.pdd.yo2.brms.domain.usecase.dto.input.RemittanceRequest;
import com.pdd.yo2.brms.domain.usecase.dto.output.RemittanceResponse;
import com.pdd.yo2.brms.port.input.RemittanceBusinessService;
import com.pdd.yo2.brms.port.output.RemittanceRuleMediatorRepository;
import com.pdd.yo2.brms.sharedkernel.exception.RemittanceException;

public class RemittanceMediator implements RemittanceBusinessService {

    private final RemittanceRuleMediatorRepository repository;

    public RemittanceMediator(RemittanceRuleMediatorRepository repository) {
        this.repository = repository;
    }

    @Override
    public RemittanceResponse doRemittance(RemittanceRequest request) throws RemittanceException {
        var remittance = Remittance.newBuilder()
                .withId(request.getTransactionId())
                .withDate(request.getTransactionDate())
                .withCustomerId(request.getCustomerId())
                .withCustomerName(request.getCustomerName())
                .withOrigin(request.getCountryOfOrigin())
                .withBeneficiaryId(request.getBeneficiaryId())
                .withBeneficiaryName(request.getBeneficiaryName())
                .withBeneficiaryCountry(request.getBeneficiaryCountry())
                .withAmount(request.getAmount())
                .withCurrency(request.getCurrencyCode())
                .build();
        remittance = repository.getScreenedRemittance(remittance);
        if (!remittance.isValidTransaction()) {
            throw new RemittanceException(remittance.getStatusMessage());
        }
        return new RemittanceResponse(remittance.getTransactionId(), remittance.getTransactionStatus());
    }
}