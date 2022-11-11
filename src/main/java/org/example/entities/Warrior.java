package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Warrior {

    private int health;
    private int attack;

    public Warrior() {
        this.health = 50;
        this.attack = 5;
    }

    public boolean isAlive() {
        return health > 0;
    }
}
