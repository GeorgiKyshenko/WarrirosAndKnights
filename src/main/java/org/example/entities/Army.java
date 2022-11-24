package org.example.entities;


import java.util.*;
import java.util.function.Supplier;

interface CanProcessCommand {
    default void processCommand(Command command, Army.WarriorInArmy sender) {
    }
}

public class Army {

    private final LinkedList<IWarrior> units;
    private WarriorInArmy tail;
//    private PublisherImpl publisher;
//
//    int countUnits;

    public Army() {
        units = new LinkedList<>();
    }

    static class WarriorInArmy implements IWarrior, WarriorBehind, CanProcessCommand {

        IWarrior warrior;
        WarriorInArmy nextWarrior;

        public WarriorInArmy(IWarrior warrior) {
            this.warrior = warrior;
        }

        @Override
        public IWarrior getWarriorBehind() {
            return nextWarrior;
        }

        private void setNextWarrior(IWarrior nextWarrior) {
            this.nextWarrior = (WarriorInArmy) nextWarrior;
        }

        @Override
        public void hit(IWarrior opponent) {
            warrior.hit(opponent);
            processCommand(ChampionHitCommand.INSTANCE, this);
        }

        public Warrior unwrapped() {
            return (Warrior) warrior;
        }

        @Override
        public void processCommand(Command command, WarriorInArmy sender) {
            if (warrior instanceof CanProcessCommand warriorProcess) {
                warriorProcess.processCommand(command, sender);
            }
            if (nextWarrior != null) {
                nextWarrior.processCommand(command, this);
            }
        }

        @Override
        public int getAttack() {
            return warrior.getAttack();
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

    private void addUnits(IWarrior warrior) {
        WarriorInArmy wrapped = new WarriorInArmy(warrior);
        if (tail != null) {
            tail.setNextWarrior(wrapped);
        }
        tail = wrapped;
        units.add(wrapped);
//        countUnits++;
//
//        if (warrior instanceof Observer) {
//            publisher.registerObserver((Observer) warrior);
//            Battle battle = new Battle(publisher);
//
//            boolean previous = units.listIterator(countUnits - 1).hasPrevious();
//            if (previous) {
//                IWarrior previousWarrior = units.get(countUnits - 2);
//
//                tail.setPreviousWarrior(previousWarrior);
//                Healer healer = (Healer) warrior;
//                healer.setWarriorToHeal(previousWarrior);
//            }
//        }
    }

    //fluent interface - Design Pattern
    public Army addUnits(Supplier<Warrior> factory, int quantity) {
        for (int i = 0; i < quantity; i++) {
            addUnits(factory.get());
        }
        return this;
    }
}
