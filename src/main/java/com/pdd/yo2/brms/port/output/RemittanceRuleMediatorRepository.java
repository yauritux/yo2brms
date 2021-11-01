package com.pdd.yo2.brms.port.output;

import com.pdd.yo2.brms.domain.entity.Remittance;

public interface RemittanceRuleMediatorRepository {

    Remittance getScreenedRemittance(Remittance remittance);
}