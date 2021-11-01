package com.pdd.yo2.brms.infrastructure.output.dao.drools;

import com.pdd.yo2.brms.domain.entity.Remittance;
import com.pdd.yo2.brms.port.output.RemittanceRuleMediatorRepository;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class RemittanceRuleMediatorRepositoryImpl implements RemittanceRuleMediatorRepository {

    private KieContainer container = KieServices.Factory.get().getKieClasspathContainer();
    private final KieSession kieSession;

    public RemittanceRuleMediatorRepositoryImpl() {
        kieSession = container.newKieSession("RemittanceValidation");
    }

    @Override
    public Remittance getScreenedRemittance(Remittance remittance) {
        kieSession.insert(remittance);
        kieSession.fireAllRules();
        kieSession.dispose();
        return remittance;
    }
}