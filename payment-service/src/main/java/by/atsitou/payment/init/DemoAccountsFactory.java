package by.atsitou.payment.init;

import java.util.Arrays;
import java.util.List;
import by.atsitou.payment.domain.PolicyAccount;

class DemoAccountsFactory {
    static List<PolicyAccount> demoAccounts() {
        return Arrays.asList(
                new PolicyAccount("POLICY_1", "231232132131"),
                new PolicyAccount("POLICY_2", "389hfswjfrh2032r"),
                new PolicyAccount("POLICY_3", "0rju130fhj20")
        );
    }
}
