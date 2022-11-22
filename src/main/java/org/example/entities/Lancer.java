package org.example.entities;

import org.example.entities.interfaces.IWarrior;

interface WarriorBehind {
    IWarrior getWarriorBehind();
}

public class Lancer extends Warrior {

    private static final int ATTACK = 6;

    private static final int PIERCING_POWER = 50;

    public Lancer() {
        super(50);
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

    @Override
    public void hit(IWarrior opponent) {
        int healthBefore = opponent.getHealth();
        super.hit(opponent);
        if (opponent instanceof WarriorBehind opponentWithNext) {
            IWarrior nextWarrior = opponentWithNext.getWarriorBehind();
            if (nextWarrior != null) {
                int healthAfter = opponent.getHealth();
                int dealtDamage = healthBefore - healthAfter;
                int percentage = 100;
                int damageToTheNext = (dealtDamage * PIERCING_POWER) / percentage;
                nextWarrior.takeDamage(damageToTheNext);
            }
        }
    }
}
