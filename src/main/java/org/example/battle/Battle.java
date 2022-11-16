package org.example.battle;

import org.example.entities.Army;
import org.example.entities.Warrior;

import java.util.Iterator;

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

            if (warrior1.isAlive()) warrior1.hit(warrior2);
            else break;


            if (warrior2.isAlive()) warrior2.hit(warrior1);
            else break;

        }
        return warrior1.isAlive();
    }

    /**
     * Epic fight between armies
     *
     * @param army1 - could be army of warriors or army of knights
     * @param army2 - could be army of warriors or army of knights
     * @return - if army1 wins or loose
     */

    public static boolean fight(Army army1, Army army2) {

        Iterator<Warrior> it1 = army1.firstAliveIterator();
        Iterator<Warrior> it2 = army2.firstAliveIterator();

        while (it1.hasNext() && it2.hasNext()) {
            fight(it1.next(), it2.next());
        }
        return it1.hasNext();
    }
}
