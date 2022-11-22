package org.example.entities;

import org.example.entities.interfaces.IWarrior;

import java.util.*;
import java.util.function.Supplier;

public class Army {

    private final Collection<IWarrior> units;
    private WarriorInArmy tail;

    public Army() {
        units = new ArrayList<>();
    }

    static class WarriorInArmy implements IWarrior, WarriorBehind {

        IWarrior warrior;
        IWarrior nextWarrior;

        public WarriorInArmy(IWarrior warrior) {
            this.warrior = warrior;
        }

        @Override
        public IWarrior getWarriorBehind() {
            return nextWarrior;
        }

        private void setNextWarrior(IWarrior nextWarrior) {
            this.nextWarrior = nextWarrior;
        }

        @Override
        public void hit(IWarrior opponent) {
            warrior.hit(opponent);
        }

        @Override
        public int getHealth() {
            return warrior.getHealth();
        }

        @Override
        public void takeDamage(int attack) {
            warrior.takeDamage(attack);
        }
    }

    public Iterator<IWarrior> firstAliveIterator() {
        return new FirstAliveIterator();
    }

    private class FirstAliveIterator implements Iterator<IWarrior> {
        Iterator<IWarrior> iterator = units.iterator();
        IWarrior warrior;


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
        public IWarrior next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return warrior;
        }
    }

    private void addUnits(Warrior warrior) {
        WarriorInArmy wrapped = new WarriorInArmy(warrior);
        if (tail != null) {
            tail.setNextWarrior(wrapped);
        }
        tail = wrapped;
        units.add(wrapped);
    }

    //fluent interface - Design Pattern
    public Army addUnits(Supplier<Warrior> factory, int quantity) {
        for (int i = 0; i < quantity; i++) {
            addUnits(factory.get());
        }
        return this;
    }
}
