package br.com.gubee.interview.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
public class PowerStatsInput {

    @NotNull
    private int strength;

    @NotNull
    private int agility;

    @NotNull
    private int dexterity;

    @NotNull
    private int intelligence;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

}
