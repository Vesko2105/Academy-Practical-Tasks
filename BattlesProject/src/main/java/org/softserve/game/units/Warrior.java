package org.softserve.game.units;

import org.softserve.game.damage.SimpleDamage;
import org.softserve.game.events.cor.RequestType;

public class Warrior extends Unit{
    private int maxHealth = 50;
    private int attack = 5;

    public Warrior() {
        this(50);
    }

    protected Warrior(int health) {
        super(health);
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    public int getAttack() {
        return attack;
    }

    @Override
    public void hits(Unit enemy) {
        enemy.hitBy(new SimpleDamage(getAttack()));
        passOnToNext(() -> RequestType.HEAL, this);
    }
}
