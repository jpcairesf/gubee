package br.com.gubee.interview.core.features.hero.service;

import br.com.gubee.interview.core.features.common.exception.RecordNotFoundException;
import br.com.gubee.interview.core.features.hero.model.HeroComparisonConverter;
import br.com.gubee.interview.core.features.hero.model.HeroComparisonOutput;
import br.com.gubee.interview.core.features.hero.repository.HeroRepository;
import br.com.gubee.interview.core.features.powerstats.service.PowerStatsService;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.HeroInput;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.PowerStatsInput;
import br.com.gubee.interview.model.enumx.RaceEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Collections.reverse;

//@SpringBootTest
class HeroServiceTest {

//    @InjectMocks
//    private HeroService heroService;
//
//    @Mock
//    private PowerStatsService powerStatsService;
//
//    @Mock
//    private HeroRepository heroRepository;
//
//    @Test
//    public void shouldCreateHero() {
//        PowerStatsInput powerStatsInput = PowerStatsInput.builder()
//                .strength(10)
//                .agility(10)
//                .dexterity(10)
//                .intelligence(10)
//                .build();
//        HeroInput input = HeroInput.builder()
//                .name("shouldCreateHero")
//                .race(RaceEnum.ALIEN)
//                .powerStatsInput(powerStatsInput)
//                .build();
//
//        UUID powerStatsId = UUID.randomUUID();
//        UUID heroId = UUID.randomUUID();
//
//        Hero expectedHero = getTestHero("shouldCreateHero", powerStatsId, heroId);
//
//        Mockito.when(powerStatsService.create(powerStatsInput)).thenReturn(powerStatsId);
//        Mockito.when(heroRepository.create(input, powerStatsId)).thenReturn(expectedHero);
//
//        Hero createdHero = heroService.create(input);
//
//        Assertions.assertEquals(expectedHero, createdHero);
//        Mockito.verify(powerStatsService).create(powerStatsInput);
//        Mockito.verify(heroRepository).create(input, powerStatsId);
//    }
//
//    @Test
//    public void shouldFindById() {
//        UUID powerStatsId = UUID.randomUUID();
//        UUID heroId = UUID.randomUUID();
//
//        Hero expectedHero = getTestHero("shouldCreateHero", powerStatsId, heroId);
//
//        Mockito.when(heroRepository.findById(heroId)).thenReturn(Optional.of(expectedHero));
//
//        Hero foundHero = heroService.findById(heroId);
//
//        Assertions.assertEquals(expectedHero, foundHero);
//        Mockito.verify(heroRepository).findById(heroId);
//    }
//
//    @Test
//    public void shouldTryFindById_ThenThrowNotFound() {
//        UUID heroId = UUID.randomUUID();
//
//        Mockito.when(heroRepository.findById(heroId)).thenReturn(Optional.empty());
//
//        Assertions.assertThrows(RecordNotFoundException.class, () -> heroService.findById(heroId));
//        Mockito.verify(heroRepository).findById(heroId);
//    }
//
//    @Test
//    public void shouldFindByFilter() {
//        UUID powerStats1Id = UUID.randomUUID();
//        UUID powerStats2Id = UUID.randomUUID();
//        UUID hero1Id = UUID.randomUUID();
//        UUID hero2Id = UUID.randomUUID();
//
//        Hero hero1 = getTestHero("shouldFind", powerStats1Id, hero1Id);
//        Hero hero2 = getTestHero("FindByFilter", powerStats2Id, hero2Id);
//
//        List<Hero> expectedHeroes = Arrays.asList(hero1, hero2);
//        String name = "Find";
//
//        Mockito.when(heroRepository.findByFilter(name)).thenReturn(expectedHeroes);
//
//        List<Hero> foundHeroes = heroService.findByFilter(name);
//
//        Assertions.assertEquals(expectedHeroes, foundHeroes);
//        Mockito.verify(heroRepository).findByFilter(name);
//    }
//
//    @Test
//    public void shouldUpdateHero() {
//        UUID heroId = UUID.randomUUID();
//        HeroInput input = HeroInput.builder().build();
//        PowerStatsInput powerStatsInput = PowerStatsInput.builder().build();
//        input.setPowerStatsInput(powerStatsInput);
//        Hero expectedHero = Hero.builder().build();
//        expectedHero.setId(heroId);
//
//        Mockito.doNothing().when(powerStatsService).update(heroId, powerStatsInput);
//        Mockito.when(heroRepository.update(heroId, input)).thenReturn(expectedHero);
//
//        Hero updatedHero = heroService.update(heroId, input);
//
//        Assertions.assertEquals(expectedHero, updatedHero);
//        Mockito.verify(powerStatsService).update(heroId, powerStatsInput);
//        Mockito.verify(heroRepository).update(heroId, input);
//    }
//
//    @Test
//    public void shouldDeleteHero() {
//        UUID heroId = UUID.randomUUID();
//        Mockito.doNothing().when(heroRepository).delete(heroId);
//        heroService.delete(heroId);
//        Mockito.verify(heroRepository).delete(heroId);
//    }
//
//    @Test
//    public void shouldGetComparisonById() {
//        UUID powerStats1Id = UUID.randomUUID();
//        UUID powerStats2Id = UUID.randomUUID();
//        UUID hero1Id = UUID.randomUUID();
//        UUID hero2Id = UUID.randomUUID();
//
//        Hero hero1 = getTestHero("shouldGetComparisonById", powerStats1Id, hero1Id);
//
//        PowerStats expectedPowerStats = PowerStats.builder()
//                .id(powerStats2Id)
//                .strength(9)
//                .agility(10)
//                .dexterity(11)
//                .intelligence(12)
//                .build();
//
//        Hero hero2 = Hero.builder()
//                .id(hero2Id)
//                .name("name")
//                .race(RaceEnum.ALIEN)
//                .powerStats(expectedPowerStats)
//                .build();
//
//        List<Hero> heroList = Arrays.asList(hero1, hero2);
//        HeroComparisonOutput expectedOutput = HeroComparisonOutput.builder()
//                .first(hero1)
//                .second(hero2)
//                .strengthResult(-1)
//                .agilityResult(0)
//                .dexterityResult(1)
//                .intelligenceResult(2)
//                .build();
//
//        if(!heroList.get(0).getId().equals(hero1Id)) reverse(heroList);
//        Pair<Hero, Hero> heroPair = Pair.of(heroList.get(0), heroList.get(1));
//
//        Mockito.when(heroRepository.getComparisonById(hero1Id, hero2Id)).thenReturn(heroList);
//        Mockito.when(HeroComparisonConverter.fromHeroes(heroPair)).thenReturn(expectedOutput);
//
//        HeroComparisonOutput output = heroService.getComparisonById(hero1Id, hero2Id);
//
//        Assertions.assertEquals(expectedOutput, output);
//        Mockito.verify(heroRepository).getComparisonById(hero1Id, hero2Id);
//    }
//
//    private Hero getTestHero(String name, UUID powerStatsId, UUID heroId) {
//        PowerStats expectedPowerStats = PowerStats.builder()
//                .id(powerStatsId)
//                .strength(10)
//                .agility(10)
//                .dexterity(10)
//                .intelligence(10)
//                .build();
//
//        return Hero.builder()
//                .id(heroId)
//                .name(name)
//                .race(RaceEnum.ALIEN)
//                .powerStats(expectedPowerStats)
//                .build();
//    }
}
