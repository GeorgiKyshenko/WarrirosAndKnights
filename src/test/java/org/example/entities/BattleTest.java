package org.example.entities;

import org.example.battle.Battle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleTest {

    private Warrior warrior1;

    private Warrior warrior2;

    private Warrior knight;


    @BeforeEach
    public void setUp() {
        warrior1 = new Warrior();
        warrior2 = new Warrior();
        knight = new Knight();
    }


    @Test
    @DisplayName("When two warriors fight, the winner is the one who strikes first")
    void testWarriorAgainstWarriorFirstWarriorToStrikeStaysAlive() {
        assertTrue(Battle.fight(warrior1, warrior2));
    }

    @Test
    @DisplayName("When two warriors fights - first stays alive, second dies")
    void testFirstWarriorIsAliveSecondWarriorIsDead() {
        Battle.fight(warrior1, warrior2);
        assertTrue(warrior1.isAlive());
        assertFalse(warrior2.isAlive());
    }

    @Test
    @DisplayName("When warrior fights against knight, warrior dies")
    void testWarriorAgainstKnightKnightStaysAlive() {
        assertFalse(Battle.fight(warrior1, knight));
    }

    @Test
    @DisplayName("When warrior fight against knight - warrior dies, knight stays alive")
    void testWarriorAgainstKnightWarriorDiesKnightStaysAlive() {
        Battle.fight(warrior1, knight);
        assertFalse(warrior1.isAlive());
        assertTrue(knight.isAlive());
    }

    @Test
    @DisplayName("When knight fights against warrior, warrior dies")
    void testKnightAgainstWarriorKnightStaysAlive() {
        Battle.fight(knight, warrior1);
    }

    @Test
    @DisplayName("When knight fight against warrior - knight stays alive, warrior dies")
    void testKnightAgainstWarriorKnightStaysAliveWarriorDies() {
        Battle.fight(knight, warrior1);
        assertTrue(knight.isAlive());
        assertFalse(warrior1.isAlive());
    }

    @Test
    @DisplayName("When a knight fights against two warriors - knight dies")
    void testWarriorAgainstKnightThenKnightFightsHealthyWarriorKnightDies() {
        Battle.fight(warrior1, knight);
        assertFalse(Battle.fight(knight, warrior2));
    }

}