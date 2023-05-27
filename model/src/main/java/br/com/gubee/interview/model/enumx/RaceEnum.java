package br.com.gubee.interview.model.enumx;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RaceEnum {

    HUMAN(1L, "HUMAN"),

    ALIEN(2L, "ALIEN"),

    DIVINE(3L, "DIVINE"),

    CYBORG(4L, "CYBORG");

    private Long id;

    private String description;



}
