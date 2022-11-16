package org.example.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter(AccessLevel.PROTECTED)
@AllArgsConstructor
public class Warrior {

    private static final int ATTACK = 5;
    private int health;

    public Warrior() {
        this(50);
    }

    public int getAttack() {
        return ATTACK;
    }

    public int getHealth() {
        return health;
    }

    public boolean isAlive() {
        return getHealth() > 0;
    }


    public void hit(Warrior opponent) {
        opponent.takeDamage(getAttack());
    }

    protected void takeDamage(int attack) {
        setHealth(getHealth() - attack);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{health=" + health + "}";
    }
}
