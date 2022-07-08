package org.softserve.game.units;

public class Knight extends Warrior {
    private int maxHealth = 50;
    private int attack = 7;

    public Knight(){
        super(50);
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public int getAttack() {
        return attack;
    }
}
