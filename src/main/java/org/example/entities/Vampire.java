package org.example.entities;

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
    public void hit(Warrior opponent) {
        if (opponent instanceof Defender) {
            hitDefender(opponent);
        } else {
            hitWarriorOrKnight(opponent);
        }
    }

    private void hitDefender(Warrior defender) {
        super.hit(defender);
        this.setHealth(getHealth() + (Math.abs((((Defender) defender).getDefense() - getAttack())) * getVampirismPercentage() / 100));
    }

    private void hitWarriorOrKnight(Warrior opponent) {
        super.hit(opponent);
        this.setHealth(getHealth() + (getAttack() * getVampirismPercentage() / 100));
    }
}
