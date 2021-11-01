package com.pdd.yo2.brms.infrastructure.input.cli;

import com.pdd.yo2.brms.infrastructure.api.grpc.dto.RemittanceRequest;
import com.pdd.yo2.brms.infrastructure.api.grpc.dto.RemittanceResponse;
import com.pdd.yo2.brms.infrastructure.api.grpc.dto.RemittanceServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.UUID;

public class RemittanceClientTester {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50055)
                .usePlaintext().build();
        RemittanceServiceGrpc.RemittanceServiceBlockingStub stub = RemittanceServiceGrpc.newBlockingStub(channel);

        RemittanceResponse helloResponse = stub.doRemittance(RemittanceRequest.newBuilder()
                        .setTransactionId(UUID.randomUUID().toString())
                        .setCustomerId("999")
                        .setCustomerName("Yauri Attamimi")
                        .setCountryOfOrigin("ID")
                        .setBeneficiaryId("777")
                        .setBeneficiaryName("Naruto")
                        .setBeneficiaryCountry("IR")
                        .setTransactionAmount(1500)
                        .setCurrency("USD")
                .build());

        System.out.println("Response from server:");
        System.out.println(helloResponse.toString());

        channel.shutdown();
    }
}
