package com.pdd.yo2.brms;

import com.pdd.yo2.brms.domain.entity.Remittance;
import com.pdd.yo2.brms.sharedkernel.constant.TransactionStatus;
import org.droolsassert.DroolsAssert;
import org.droolsassert.DroolsSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.UUID;

@DroolsSession(resources = {
        "classpath:/com/pdd/yo2/brms/remittance/drls/RemittanceValidation.drl"
},
        logResources = true)
public class RemittanceBusinessRuleTest {

    @RegisterExtension
    public DroolsAssert drools = new DroolsAssert();

    @Test
    public void restrictRemittanceToIran() {
        Remittance remittance = Remittance.newBuilder()
                .withId(UUID.randomUUID().toString())
                .withAmount(BigDecimal.valueOf(100))
                .withCustomerId("123")
                .withCustomerName("Yauri")
                .withOrigin("ID")
                .withBeneficiaryId("555")
                .withBeneficiaryName("Mashaek")
                .withBeneficiaryCountry("IR")
                .build();
        drools.insertAndFire(remittance);
        drools.assertExist(remittance);
        assertEquals(TransactionStatus.REJECTED, remittance.getTransactionStatus());
    }

    @Test
    public void restrictRemittanceFromIran() {
        Remittance remittance = Remittance.newBuilder()
                .withId(UUID.randomUUID().toString())
                .withAmount(BigDecimal.valueOf(100))
                .withCustomerId("555")
                .withCustomerName("Mashaek")
                .withOrigin("IR")
                .withBeneficiaryId("123")
                .withBeneficiaryName("Yauri")
                .withBeneficiaryCountry("ID")
                .build();
        drools.insertAndFire(remittance);
        drools.assertExist(remittance);
        assertEquals(TransactionStatus.REJECTED, remittance.getTransactionStatus());
    }
}
