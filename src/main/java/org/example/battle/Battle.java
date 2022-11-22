package org.example.battle;

import org.example.entities.Army;
import org.example.entities.Warrior;
import org.example.entities.interfaces.IWarrior;

import java.util.Iterator;

public class Battle {

    /**
     * Fighting between different type of Warriors
     *
     * @param warrior1 - Warrior type1
     * @param warrior2 - Warrior type2
     * @return if warrior1 is alive or dead
     */

    public static boolean fight(IWarrior warrior1, IWarrior warrior2) {

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

        Iterator<IWarrior> it1 = army1.firstAliveIterator();
        Iterator<IWarrior> it2 = army2.firstAliveIterator();

        while (it1.hasNext() && it2.hasNext()) {
            fight(it1.next(), it2.next());
        }
        return it1.hasNext();
    }
}
