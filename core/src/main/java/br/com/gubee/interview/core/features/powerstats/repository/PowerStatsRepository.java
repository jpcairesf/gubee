package br.com.gubee.interview.core.features.powerstats.repository;

import br.com.gubee.interview.model.PowerStatsInput;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PowerStatsRepository {

    UUID create(PowerStatsInput input);

    void update(UUID id, PowerStatsInput input);
}
