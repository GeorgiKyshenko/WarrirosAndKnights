package org.example.entities.interfaces;

public interface IWarrior {

    void hit(IWarrior opponent);

    int getHealth();

    void takeDamage(int attack);

    default boolean isAlive() {
        return getHealth() > 0;
    }
}
