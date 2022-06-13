package by.atsitou.policy.search.readmodel;

import io.reactivex.Maybe;
import java.util.List;
import by.atsitou.policy.search.service.api.v1.queries.findpolicy.FindPolicyQuery;

public interface PolicyViewRepository {

    Maybe<List<PolicyView>> findAll(FindPolicyQuery query);

    void save(PolicyView view);
}
