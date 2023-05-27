package br.com.gubee.interview.core.features.hero.model;

import br.com.gubee.interview.model.Hero;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HeroComparisonOutput {

    private Hero first;

    private Hero second;

    private int strengthResult;

    private int agilityResult;

    private int dexterityResult;

    private int intelligenceResult;

}
