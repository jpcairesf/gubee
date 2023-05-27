package br.com.gubee.interview.core.features.hero.repository;

import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.HeroInput;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface HeroRepository {

    Hero create(HeroInput input, UUID powerStatsId);

    Optional<Hero> findById(UUID id);

    List<Hero> findByFilter(String name);

    Hero update(UUID id, HeroInput input);

    void delete(UUID id);

    List<Hero> getComparisonById(UUID first, UUID second);

    boolean existsByName(String name);
}
