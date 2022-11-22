package org.example.entities;

import org.example.entities.interfaces.IWarrior;

public class Vampire extends Warrior {

    private static final int ATTACK = 4;
    private static final int VAMPIRISM_PERCENTAGE = 50;

    public Vampire() {
        super(40);
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

    public static int getVampirismPercentage() {
        return VAMPIRISM_PERCENTAGE;
    }

    @Override
    public void hit(IWarrior opponent) {
        int healthBefore = opponent.getHealth();
        super.hit(opponent);
        int healthAfter = opponent.getHealth();
        int dealtDamage = healthBefore - healthAfter;
        int healingPoints = (dealtDamage * 50) / 100;
        healBy(healingPoints);
    }
}
