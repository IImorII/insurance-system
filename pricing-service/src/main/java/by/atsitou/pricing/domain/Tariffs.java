package by.atsitou.pricing.domain;

import java.util.Optional;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.annotation.*;

@Repository
public interface Tariffs extends CrudRepository<Tariff, Long>  {

    Optional<Tariff> findByCode(String code);
    
    Tariff getByCode(String code);
}
