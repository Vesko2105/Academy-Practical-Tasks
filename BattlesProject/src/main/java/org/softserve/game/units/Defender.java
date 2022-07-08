package org.softserve.game.units;

import org.softserve.game.damage.Damage;
import org.softserve.game.damage.SimpleDamage;

public class Defender extends Warrior{
    private static int MAX_HEALTH = 60;
    private static int ATTACK = 3;
    private static int DEFENSE = 2;

    public Defender(){
        super(60);
    }

    @Override
    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

    public static int getDefence() {
        return DEFENSE;
    }

    @Override
    protected void hitBy(Damage damage) {
        super.hitBy(new SimpleDamage(Math.max(0, damage.getDamage() - getDefence())));
    }
}
