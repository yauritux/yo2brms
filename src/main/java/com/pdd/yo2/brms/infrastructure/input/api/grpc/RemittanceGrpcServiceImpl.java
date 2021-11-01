package com.pdd.yo2.brms.infrastructure.input.api.grpc;

import com.pdd.yo2.brms.domain.usecase.aggregate.RemittanceMediator;
import com.pdd.yo2.brms.infrastructure.api.grpc.dto.RemittanceServiceGrpc;
import com.pdd.yo2.brms.infrastructure.api.grpc.dto.TransactionStatus;
import com.pdd.yo2.brms.port.output.RemittanceRuleMediatorRepository;
import com.pdd.yo2.brms.infrastructure.api.grpc.dto.RemittanceRequest;
import com.pdd.yo2.brms.infrastructure.api.grpc.dto.RemittanceResponse;
import com.pdd.yo2.brms.sharedkernel.exception.RemittanceException;
import io.grpc.stub.StreamObserver;

import java.math.BigDecimal;

public class RemittanceGrpcServiceImpl extends RemittanceServiceGrpc.RemittanceServiceImplBase {

    private final RemittanceRuleMediatorRepository remittanceRuleRepository;

    public RemittanceGrpcServiceImpl(RemittanceRuleMediatorRepository remittanceRuleRepository) {
        this.remittanceRuleRepository = remittanceRuleRepository;
    }

    @Override
    public void doRemittance(RemittanceRequest request, StreamObserver<RemittanceResponse> responseObserver) {
        var service = new RemittanceMediator(remittanceRuleRepository);
        var remittanceRequest = com.pdd.yo2.brms.domain.usecase.dto.input.RemittanceRequest.newBuilder()
                .withTransactionId(request.getTransactionId())
                .withCustomerId(request.getCustomerId())
                .withCustomerName(request.getCustomerName())
                .withOrigin(request.getCountryOfOrigin())
                .withBeneficiaryId(request.getBeneficiaryId())
                .withBeneficiaryName(request.getBeneficiaryName())
                .withBeneficiaryCountry(request.getBeneficiaryCountry())
                .withAmount(BigDecimal.valueOf(request.getTransactionAmount()))
                .withCurrency(request.getCurrency())
                .build();

        RemittanceResponse.Builder response = RemittanceResponse.newBuilder()
                .setTransactionId(request.getTransactionId());
        try {
            var remittanceResponse = service.doRemittance(remittanceRequest);
            response.setTransactionStatus(TransactionStatus.valueOf(remittanceResponse.getTransactionStatus().toString()));
            response.setStatusMessage("OK");
        } catch (RemittanceException e) {
            response.setStatusMessage(e.getMessage());
            response.setTransactionStatus(TransactionStatus.REJECTED);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatusMessage(e.getMessage());
            response.setTransactionStatus(TransactionStatus.ERROR);
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}