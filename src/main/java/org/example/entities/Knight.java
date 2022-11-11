package org.example.entities;

public class Knight extends Warrior {

    public Knight() {
        setHealth(50);
        setAttack(7);
    }

    public Knight(int health, int attack) {
        setHealth(health);
        setAttack(attack);
    }
}
