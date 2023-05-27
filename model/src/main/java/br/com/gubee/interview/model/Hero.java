package br.com.gubee.interview.model;

import br.com.gubee.interview.model.enumx.RaceEnum;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Hero {

    private UUID id;
    private String name;
    private RaceEnum race;
    private PowerStats powerStats;
    private boolean enabled;
    private String createdAt;
    private String updatedAt;

}
