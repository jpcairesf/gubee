package br.com.gubee.interview.core.features.hero.service;

import br.com.gubee.interview.core.features.common.exception.RecordNotFoundException;
import br.com.gubee.interview.core.features.hero.model.HeroComparisonConverter;
import br.com.gubee.interview.core.features.hero.model.HeroComparisonOutput;
import br.com.gubee.interview.core.features.hero.repository.HeroRepository;
import br.com.gubee.interview.core.features.powerstats.service.PowerStatsService;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.HeroInput;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static java.lang.String.format;
import static java.util.Collections.reverse;

@Service
@RequiredArgsConstructor
public class HeroService {

    private final PowerStatsService powerStatsService;
    private final HeroRepository repository;

    @Transactional
    public Hero create(HeroInput input) {
        UUID powerStatsId = powerStatsService.create(input.getPowerStatsInput());
        return repository.create(input, powerStatsId);
    }

    @Transactional(readOnly = true)
    public Hero findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(format("Não encontrado Hero de ID \"%s\".", id.toString())));
    }

    @Transactional(readOnly = true)
    public List<Hero> findByFilter(String name) {
        return repository.findByFilter(name);
    }

    @Transactional
    public Hero update(UUID id, HeroInput input) {
        input.setUpdatedAt(LocalDateTime.now());
        input.getPowerStatsInput().setUpdatedAt(LocalDateTime.now());
        powerStatsService.update(id, input.getPowerStatsInput());
        return repository.update(id, input);
    }

    @Transactional
    public void delete(UUID id) {
        repository.delete(id);
    }

    @Transactional
    public HeroComparisonOutput getComparisonById(UUID first, UUID second) {
        List<Hero> heroList = repository.getComparisonById(first, second);

        if(!heroList.get(0).getId().equals(first)) reverse(heroList);

        Pair<Hero, Hero> heroPair = Pair.of(heroList.get(0), heroList.get(1));
        return HeroComparisonConverter.fromHeroes(heroPair);
    }
}
