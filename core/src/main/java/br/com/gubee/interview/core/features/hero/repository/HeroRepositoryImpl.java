package br.com.gubee.interview.core.features.hero.repository;

import br.com.gubee.interview.core.features.hero.model.HeroRowMapper;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.HeroInput;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static br.com.gubee.interview.core.features.common.utils.RepositoryUtils.UUIDMapper;

@Repository
@RequiredArgsConstructor
public class HeroRepositoryImpl implements HeroRepository {

    private final NamedParameterJdbcTemplate template;

    @Override
    public Hero create(HeroInput input, UUID powerStatsId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("name", input.getName())
                .addValue("race", input.getRace().getDescription())
                .addValue("created_at", input.getCreatedAt())
                .addValue("updated_at", input.getUpdatedAt())
                .addValue("power_stats_id", powerStatsId);

        String sql = "INSERT INTO hero(name, race, power_stats_id, created_at, updated_at) " +
                "VALUES(:name, :race, :power_stats_id, :created_at, :updated_at) RETURNING id";

        UUID id = template.queryForObject(sql, parameterSource, UUIDMapper());

        return findById(id).get();
    }

    @Override
    public Optional<Hero> findById(UUID id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("id", id);

        String sql = "SELECT hero.id, hero.name, hero.race, hero.power_stats_id, hero.enabled, hero.created_at, hero.updated_at, " +
                "power_stats.strength, power_stats.agility, power_stats.dexterity, power_stats.intelligence, " +
                "power_stats.created_at AS power_stats_created_at, power_stats.updated_at AS power_stats_updated_at " +
                "FROM hero JOIN power_stats ON power_stats.id = hero.power_stats_id " +
                "WHERE hero.id = :id";

        return Optional.ofNullable(template.queryForObject(sql, parameterSource, new HeroRowMapper()));
    }

    @Override
    public List<Hero> findByFilter(String name) {
        if(name == null) name = "";
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("name", "%" + name + "%");

        String sql = "SELECT hero.id, hero.name, hero.race, hero.power_stats_id, hero.enabled, hero.created_at, hero.updated_at, " +
                "power_stats.strength, power_stats.agility, power_stats.dexterity, power_stats.intelligence, " +
                "power_stats.created_at AS power_stats_created_at, power_stats.updated_at AS power_stats_updated_at " +
                "FROM hero JOIN power_stats ON power_stats.id = hero.power_stats_id " +
                "WHERE hero.name LIKE :name";

        return template.query(sql, parameterSource, new HeroRowMapper());
    }

    @Override
    public Hero update(UUID id, HeroInput input) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("name", input.getName())
                .addValue("race", input.getRace().getDescription())
                .addValue("enabled", input.isEnabled())
                .addValue("updated_at", input.getUpdatedAt())
                .addValue("id", id);

        String sql = "UPDATE hero SET name = :name, race = :race, updated_at = :updated_at, enabled = :enabled WHERE id = :id";

        template.update(sql, parameterSource);

        return findById(id).get();
    }

    @Override
    public void delete(UUID id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("id", id);
        String sql = "DELETE FROM hero WHERE id = :id CASCADE";
        template.update(sql, parameterSource);
    }

    @Override
    public List<Hero> getComparisonById(UUID first, UUID second) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("first", first)
                .addValue("second", second);

        String sql = "SELECT hero.id, hero.name, hero.race, hero.power_stats_id, hero.enabled, hero.created_at, hero.updated_at, " +
                "power_stats.strength, power_stats.agility, power_stats.dexterity, power_stats.intelligence, " +
                "power_stats.created_at AS power_stats_created_at, power_stats.updated_at AS power_stats_updated_at " +
                "FROM hero JOIN power_stats ON power_stats.id = hero.power_stats_id " +
                "WHERE hero.id IN (:first, :second)";

        return template.query(sql, parameterSource, new HeroRowMapper());
    }

    @Override
    public boolean existsByName(String name) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("name", "%" + name + "%");
        String sql = "SELECT COUNT(*) FROM hero WHERE hero.name = :name";
        return template.queryForObject(sql, parameterSource, Integer.class) > 0;
    }
}
