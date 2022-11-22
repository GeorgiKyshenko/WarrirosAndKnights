package org.example.entities;

import org.example.entities.interfaces.IWarrior;

import java.util.function.BiConsumer;

public class Warrior implements IWarrior {

    private static final int ATTACK = 5;
    private int health;
    private final int initialHealth;

    public Warrior() {
        this(50);
    }

    protected Warrior(int health) {
        initialHealth = this.health = health;
    }

    public int getAttack() {
        return ATTACK;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = Math.min(initialHealth, health);
    }

    protected void healBy(int healingPoints) {
        setHealth(getHealth() + healingPoints);
    }

    @Override
    public void hit(IWarrior opponent) {
        opponent.takeDamage(getAttack());
    }

    @Override
    public void takeDamage(int attack) {
        setHealth(getHealth() - attack);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{health=" + health + "}";
    }
}
