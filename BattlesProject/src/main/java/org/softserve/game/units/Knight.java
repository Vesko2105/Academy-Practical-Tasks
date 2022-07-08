package org.softserve.game.units;

public class Knight extends Warrior {
    private static int MAX_HEALTH = 50;
    private static int ATTACK = 7;

    public Knight(){
        super(50);
    }

    @Override
    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }
}
