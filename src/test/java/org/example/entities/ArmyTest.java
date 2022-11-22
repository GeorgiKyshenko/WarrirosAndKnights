package org.example.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class ArmyTest {

    Army army;

    @BeforeEach
    void setUp() {
        army = new Army();
    }

    @Test
    @DisplayName("Adding unit - Army shouldn`t be empty")
    void testArmyAddingUnits() {

        army.addUnits(Warrior::new, 1);
        assertTrue(army.firstAliveIterator().hasNext());
    }

}