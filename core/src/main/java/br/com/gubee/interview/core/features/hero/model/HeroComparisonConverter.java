package br.com.gubee.interview.core.features.hero.model;

import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Pair;

@NoArgsConstructor(access = AccessLevel.NONE)
public class HeroComparisonConverter {

    public static HeroComparisonOutput fromHeroes(Pair<Hero, Hero> heroes) {
        PowerStats firstStats = heroes.getFirst().getPowerStats();
        PowerStats secondStats = heroes.getSecond().getPowerStats();
        int strengthResult = firstStats.getStrength() - secondStats.getStrength();
        int agilityResult = firstStats.getAgility() - secondStats.getAgility();
        int dexterityResult = firstStats.getDexterity() - secondStats.getDexterity();
        int intelligenceResult = firstStats.getIntelligence() - secondStats.getIntelligence();

        return HeroComparisonOutput.builder()
                .first(heroes.getFirst())
                .second(heroes.getSecond())
                .strengthResult(strengthResult)
                .agilityResult(agilityResult)
                .dexterityResult(dexterityResult)
                .intelligenceResult(intelligenceResult)
                .build();
    }

}
