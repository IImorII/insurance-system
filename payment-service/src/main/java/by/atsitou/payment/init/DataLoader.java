package by.atsitou.payment.init;

import by.atsitou.payment.domain.PolicyAccount;
import by.atsitou.payment.domain.PolicyAccountRepository;

import javax.inject.Singleton;
import javax.transaction.Transactional;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class DataLoader  implements ApplicationEventListener<ServerStartupEvent> {
    private final PolicyAccountRepository policyAccountDb;

    @Transactional
    @Override
    public void onApplicationEvent(ServerStartupEvent event) {
        DemoAccountsFactory.demoAccounts().forEach(this::addIfNotExists);
        log.info("Demo data added");
    }
    
    private void addIfNotExists(PolicyAccount account) {
        if (!policyAccountDb.findByPolicyAccountNumber(account.getPolicyAccountNumber()).isPresent()) {
            policyAccountDb.save(account);
        }
    }
}
