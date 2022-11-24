package org.example.entities;

public class Healer extends Warrior implements CanProcessCommand {

    private static final int HEALING_POWER = 2;

    public Healer() {
        super(60);
    }

    public static int getHealingPower() {
        return HEALING_POWER;
    }

//    public void setWarriorToHeal(IWarrior warriorToHeal) {
//        this.warriorToHeal = warriorToHeal;
//    }
//
//    public IWarrior getWarriorToHeal() {
//        return warriorToHeal;
//    }

    @Override
    public void hit(IWarrior opponent) {
        // do nothing
    }

    @Override
    public void processCommand(Command command, Army.WarriorInArmy sender) {
        if (command instanceof ChampionHitCommand) {
            if (this.isAlive()) {
                heal(sender);
            }
        }
    }

    public void heal(IWarrior warriorToHeal) {
        if (warriorToHeal instanceof Army.WarriorInArmy warrior) {
            Warrior unwrapped = warrior.unwrapped();
            unwrapped.healBy(getHealingPower());
        }
    }
}
