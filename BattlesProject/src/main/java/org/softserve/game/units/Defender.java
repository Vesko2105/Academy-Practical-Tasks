package org.softserve.game.units;

import org.softserve.game.damage.Damage;
import org.softserve.game.damage.SimpleDamage;

public class Defender extends Warrior{
    private int maxHealth = 60;
    private int attack = 3;
    private int defense = 2;

    public Defender(){
        super(60);
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defense;
    }

    @Override
    protected void hitBy(Damage damage) {
        super.hitBy(new SimpleDamage(Math.max(0, damage.getDamage() - getDefence())));
    }
}
