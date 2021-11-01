package com.pdd.yo2.brms.port.input;

import com.pdd.yo2.brms.domain.usecase.dto.input.RemittanceRequest;
import com.pdd.yo2.brms.domain.usecase.dto.output.RemittanceResponse;
import com.pdd.yo2.brms.sharedkernel.exception.RemittanceException;

public interface RemittanceService {

    public RemittanceResponse doRemittance(RemittanceRequest request) throws RemittanceException;
}
