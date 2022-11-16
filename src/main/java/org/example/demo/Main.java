package org.example.demo;

import org.example.battle.Battle;
import org.example.entities.*;


public class Main {
    public static void main(String[] args) {


        Warrior warrior1 = new Warrior();
        Warrior warrior2 = new Warrior();

        Warrior knight1 = new Knight();
        Warrior knight2 = new Knight();

        Warrior defender1 = new Defender();
        Warrior defender2 = new Defender();

        Warrior vampire1 = new Vampire();
        Warrior vampire2 = new Vampire();


        System.out.println(Battle.fight(vampire1, vampire2));

        System.out.println(vampire1.getHealth());
        System.out.println(vampire2.getHealth());



//        Army myArmy = new Army().addUnits(Warrior::new, 10);
    }
}