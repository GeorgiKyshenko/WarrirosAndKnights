package org.example.entities;

interface Command{}

enum ChampionHitCommand implements Command {
    INSTANCE
}

public interface IWarrior {
    default void hit(IWarrior opponent) {
        opponent.takeDamage(getAttack());
    }
    int getAttack();

    int getHealth();

    void takeDamage(int attack);

    default boolean isAlive() {
        return getHealth() > 0;
    }
}
