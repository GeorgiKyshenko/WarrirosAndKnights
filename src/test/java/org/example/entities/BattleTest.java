package org.example.entities;

import org.example.battle.Battle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BattleTest {

    private Warrior warrior1;

    private Warrior warrior2;

    private Warrior knight;

    private Army army1;

    private Army army2;

    private Warrior defender;


    @BeforeEach
    public void setUp() {
        warrior1 = new Warrior();
        warrior2 = new Warrior();
        knight = new Knight();
        army1 = new Army();
        army2 = new Army();
        defender = new Defender();
    }

    static class Rookie extends Warrior {
        @Override
        public int getAttack() {
            return 1;
        }
    }


    @Test
    @DisplayName("Fight 1: When two warriors fight, the winner is the one who strikes first")
    void testWarriorAgainstWarriorFirstWarriorToStrikeStaysAlive() {
        assertTrue(Battle.fight(warrior1, warrior2));
    }

    @Test
    @DisplayName("Fight 2: When two warriors fights - first stays alive, second dies")
    void testFirstWarriorIsAliveSecondWarriorIsDead() {
        Battle.fight(warrior1, warrior2);
        assertTrue(warrior1.isAlive());
        assertFalse(warrior2.isAlive());
    }

    @Test
    @DisplayName("Fight 3: When warrior fights against knight, warrior dies")
    void testWarriorAgainstKnightKnightStaysAlive() {
        assertFalse(Battle.fight(warrior1, knight));
    }

    @Test
    @DisplayName("Fight 4: When warrior fight against knight - warrior dies, knight stays alive")
    void testWarriorAgainstKnightWarriorDiesKnightStaysAlive() {
        Battle.fight(warrior1, knight);
        assertFalse(warrior1.isAlive());
        assertTrue(knight.isAlive());
    }

    @Test
    @DisplayName("Fight 5: When knight fights against warrior, warrior dies")
    void testKnightAgainstWarriorKnightStaysAlive() {
        assertTrue(Battle.fight(knight, warrior1));
    }

    @Test
    @DisplayName("Fight 6: When knight fight against warrior - knight stays alive, warrior dies")
    void testKnightAgainstWarriorKnightStaysAliveWarriorDies() {
        Battle.fight(knight, warrior1);
        assertTrue(knight.isAlive());
        assertFalse(warrior1.isAlive());
    }

    @Test
    @DisplayName("Fight 7: When a knight fights against two warriors - knight dies")
    void testWarriorAgainstKnightThenKnightFightsHealthyWarriorKnightDies() {
        Battle.fight(warrior1, knight);
        assertFalse(Battle.fight(knight, warrior2));
    }

    @Test
    @DisplayName("Fight 8: Checking health points after the fight between warrior and defender")
    void testWarriorAgainstDefenderCheckingFinalHealthPoints() {
        Battle.fight(warrior1, defender);

        assertEquals(-1, warrior1.getHealth());
        assertEquals(9, defender.getHealth());
    }

    @Test
    @DisplayName("Fight 9: Defender against Knight - Defender loses")
    void testDefenderAgainstKnight() {
        assertFalse(Battle.fight(defender, knight));
    }

    @Test
    @DisplayName("Army Fight 1: When two armies of warriors fight, the winner is army N1")
    void testTwoArmiesOfWarriorsFightFirstArmyWin() {
        army1.addUnits(Warrior::new, 3);
        army2.addUnits(Warrior::new, 3);
        assertTrue(Battle.fight(army1, army2));
    }

    @DisplayName("Army Fight 2: Two Armies Fight")
    @ParameterizedTest
    @MethodSource
    void testTwoArmiesFight(Army army1, Army army2, boolean expected) {

        boolean fightState = Battle.fight(army1, army2);
        assertEquals(expected, fightState);
    }

    static Stream<Arguments> testTwoArmiesFight() {
        return Stream.of(
                arguments(new Army().addUnits(Warrior::new, 5),
                        new Army().addUnits(Knight::new, 5), false),

                arguments(new Army().addUnits(Warrior::new, 5),
                        new Army().addUnits(Warrior::new, 5), true),

                arguments(new Army().addUnits(Defender::new, 5),
                        new Army().addUnits(Warrior::new, 5), true),

                arguments(new Army().addUnits(Defender::new, 5),
                        new Army().addUnits(Knight::new, 5), false)
        );
    }

    @Test
    @DisplayName("Defender Test 1: When attack is lower than defenders defense, his health stays the same")
    void testDefenderDoesntTakeDamage() {

//        Warrior warrior = Mockito.mock(Warrior.class);
//        Mockito.when(warrior.getAttack()).thenReturn(1);
//        Mockito.when(warrior.getHealth()).thenReturn(10);


        Warrior rookie = new Rookie();

        Battle.fight(rookie, defender);

        assertEquals(60, defender.getHealth());
    }

    @DisplayName("Army Fight 3: Mixed Army of Warrior, Knights and Vampires fight each other")
    @ParameterizedTest
    @MethodSource
    void testMixedArmyFights(Army army1, Army army2, boolean expected) {

        boolean fightState = Battle.fight(army1, army2);
        assertEquals(expected, fightState);
    }

    static Stream<Arguments> testMixedArmyFights() {
        return Stream.of(
                arguments(new Army().addUnits(Defender::new, 2).addUnits(Vampire::new, 2).addUnits(Warrior::new, 1),
                        new Army().addUnits(Warrior::new, 2).addUnits(Defender::new, 2).addUnits(Vampire::new, 3), false),

                arguments(new Army().addUnits(Warrior::new, 1).addUnits(Defender::new, 4),
                        new Army().addUnits(Vampire::new, 3).addUnits(Warrior::new, 2), true)
        );
    }
}