package by.atsitou.policy.infrastructure.adapters.mock;

import by.atsitou.policy.domain.OfferRepository;
import by.atsitou.policy.domain.Offer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Singleton;
import javax.transaction.Transactional;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;

@Replaces(OfferRepository.class)
@Requires(env = Environment.TEST)
@Singleton
public class MockOfferRepository implements OfferRepository {

    private Map<String, Offer> map = new ConcurrentHashMap<>();

    @Transactional
    @Override
    public Offer save(Offer offer) {
        map.put(offer.getNumber(), offer);
        return offer;
    }

    @Transactional
    @Override
    public Offer getByNumber(String number) {
        return map.get(number);
    }

}
