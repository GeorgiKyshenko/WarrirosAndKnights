package org.example.entities;

public class Healer extends Warrior {

    private static final int HEALING_POWER = 2;

    public Healer() {
        super(60);
    }

    public static int getHealingPower() {
        return HEALING_POWER;
    }
}
