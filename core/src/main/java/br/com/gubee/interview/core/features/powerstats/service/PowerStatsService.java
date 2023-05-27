package br.com.gubee.interview.core.features.powerstats.service;

import br.com.gubee.interview.core.features.powerstats.repository.PowerStatsRepository;
import br.com.gubee.interview.model.PowerStatsInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PowerStatsService {

    private final PowerStatsRepository repository;

    public UUID create(PowerStatsInput input) {
        return repository.create(input);
    }

    public void update(UUID id, PowerStatsInput input) {
        repository.update(id, input);
    }

}
