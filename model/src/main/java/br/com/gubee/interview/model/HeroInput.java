package br.com.gubee.interview.model;

import br.com.gubee.interview.model.enumx.RaceEnum;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
public class HeroInput {

    @NotNull
    private String name;

    @NotNull
    private RaceEnum race;

    @NotNull
    private PowerStatsInput powerStatsInput;

    @Builder.Default
    private boolean enabled = true;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

}
