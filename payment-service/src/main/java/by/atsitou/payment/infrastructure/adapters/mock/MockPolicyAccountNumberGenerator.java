package by.atsitou.payment.infrastructure.adapters.mock;

import by.atsitou.payment.domain.PolicyAccountNumberGenerator;

import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class MockPolicyAccountNumberGenerator implements PolicyAccountNumberGenerator {

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
