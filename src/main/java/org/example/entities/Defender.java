package org.example.entities;



public class Defender extends Warrior {

    private static final int ATTACK = 3;

    private static final int DEFENSE = 2;

    public Defender() {
        super(60);
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

    public int getDefense() {
        return DEFENSE;
    }

    @Override
    protected void takeDamage(int attack) {
        if (attack > getDefense()) {
            setHealth(getHealth() - Math.abs(getDefense() - attack));
        } else {
            setHealth(getHealth());
        }
    }
}
