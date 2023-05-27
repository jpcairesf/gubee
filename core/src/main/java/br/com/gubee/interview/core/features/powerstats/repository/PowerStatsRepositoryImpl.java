package br.com.gubee.interview.core.features.powerstats.repository;

import br.com.gubee.interview.model.PowerStatsInput;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import static br.com.gubee.interview.core.features.common.utils.RepositoryUtils.UUIDMapper;

@Repository
@RequiredArgsConstructor
public class PowerStatsRepositoryImpl implements PowerStatsRepository {

    private final NamedParameterJdbcTemplate template;

    @Override
    public UUID create(PowerStatsInput input) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("strength", input.getStrength())
                .addValue("agility", input.getAgility())
                .addValue("dexterity", input.getDexterity())
                .addValue("intelligence", input.getIntelligence())
                .addValue("created_at", input.getCreatedAt())
                .addValue("updated_at", input.getUpdatedAt());


        String sql = "INSERT INTO power_stats(strength, agility, dexterity, intelligence, created_at, updated_at) " +
                "VALUES(:strength, :agility, :dexterity, :intelligence, :created_at, :updated_at) RETURNING id";

        return template.queryForObject(sql, parameterSource, UUIDMapper());
    }

    @Override
    public void update(UUID heroId, PowerStatsInput input) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("heroId", heroId)
                .addValue("strength", input.getStrength())
                .addValue("agility", input.getAgility())
                .addValue("dexterity", input.getDexterity())
                .addValue("intelligence", input.getIntelligence())
                .addValue("updatedAt", input.getUpdatedAt());

        String sql = "UPDATE power_stats " +
                "SET strength = :strength, agility = :agility, dexterity = :dexterity, intelligence = :intelligence " +
                "FROM hero WHERE power_stats.id = hero.power_stats_id AND hero.id = :heroId";

        template.update(sql, parameterSource);
    }

}
