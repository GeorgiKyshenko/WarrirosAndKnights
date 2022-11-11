package org.example.battle;

import org.example.entities.Warrior;

public class Battle {

    /**
     * Fighting between Warriors and Knights
     *
     * @param warrior1 - might be a warrior or knight
     * @param warrior2 - might be a warrior or knight
     * @return if warrior1 is alive
     */

    public static boolean fight(Warrior warrior1, Warrior warrior2) {

        while (true) {

            if (warrior1.isAlive()) warrior2.setHealth(warrior2.getHealth() - warrior1.getAttack());
            else break;


            if (warrior2.isAlive()) warrior1.setHealth(warrior1.getHealth() - warrior2.getAttack());
            else break;

        }
        return warrior1.isAlive();
    }
}
