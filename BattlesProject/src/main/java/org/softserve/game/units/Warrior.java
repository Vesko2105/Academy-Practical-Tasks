package org.softserve.game.units;

import org.softserve.game.damage.SimpleDamage;
import org.softserve.game.events.Request;

public class Warrior extends Unit{
    private static int MAX_HEALTH = 50;
    private static int ATTACK = 5;

    public Warrior() {
        this(50);
    }

    protected Warrior(int health) {
        super(health);
    }

    @Override
    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    public int getAttack() {
        return ATTACK;
    }

    @Override
    public void hits(Unit enemy) {
        enemy.hitBy(new SimpleDamage(getAttack()));
        handle(Request.HEAL, this);
    }
}
