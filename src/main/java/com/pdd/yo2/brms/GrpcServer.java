package com.pdd.yo2.brms;

import com.pdd.yo2.brms.infrastructure.input.api.grpc.RemittanceGrpcServiceImpl;
import com.pdd.yo2.brms.infrastructure.output.dao.drools.RemittanceRuleMediatorRepositoryImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {

    public static void main(String[] args) {
        var remittanceRuleRepository = new RemittanceRuleMediatorRepositoryImpl();
        var remittanceService = new RemittanceGrpcServiceImpl(remittanceRuleRepository);
        Server server = ServerBuilder
                .forPort(50055)
                .addService(remittanceService).build();
        try {
            server.start();
            server.awaitTermination();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
