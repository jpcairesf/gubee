package br.com.gubee.interview.core.features.hero.model;

import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.enumx.RaceEnum;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class HeroRowMapper implements RowMapper<Hero> {

    @Override
    public Hero mapRow(ResultSet rs, int rowNum) throws SQLException {
        PowerStats powerStats = PowerStats.builder()
                .id((UUID) rs.getObject("power_stats_id"))
                .strength(rs.getInt("strength"))
                .agility(rs.getInt("agility"))
                .dexterity(rs.getInt("dexterity"))
                .intelligence(rs.getInt("intelligence"))
                .createdAt(rs.getString("power_stats_created_at"))
                .updatedAt(rs.getString("power_stats_updated_at"))
                .build();

        return Hero.builder()
                .id((UUID) rs.getObject("id"))
                .name(rs.getString("name"))
                .race(RaceEnum.valueOf(rs.getString("race")))
                .enabled(rs.getBoolean("enabled"))
                .createdAt(rs.getString("created_at")) //change to LocalDateTime
                .updatedAt(rs.getString("updated_at"))
                .powerStats(powerStats)
                .build();
    }
}
