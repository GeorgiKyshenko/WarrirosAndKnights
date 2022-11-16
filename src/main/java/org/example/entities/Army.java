package org.example.entities;

import java.util.*;
import java.util.function.Supplier;

public class Army {

    Collection<Warrior> units;

    public Army() {
        units = new ArrayList<>();
    }

    public Iterator<Warrior> firstAliveIterator() {
        return new FirstAliveIterator();
    }

    class FirstAliveIterator implements Iterator<Warrior> {
        Iterator<Warrior> iterator = units.iterator();
        Warrior warrior;

        @Override
        public boolean hasNext() {
            if (warrior == null || !warrior.isAlive()) {
                if (iterator.hasNext()) {
                    warrior = iterator.next();
                    return true;
                } else {
                    return false;
                }
            }
            return true;
        }

        @Override
        public Warrior next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return warrior;
        }
    }

    private void addUnits(Warrior warrior) {
        units.add(warrior);
    }


    //fluent interface - Design Pattern
    public Army addUnits(Supplier<Warrior> factory, int quantity) {
        for (int i = 0; i < quantity; i++) {
            addUnits(factory.get());
        }
        return this;
    }
}
