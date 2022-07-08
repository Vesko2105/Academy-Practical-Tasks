package org.softserve.game.units;

import org.softserve.game.damage.SimpleDamage;
import org.softserve.game.events.cor.PierceHitRequest;
import org.softserve.game.events.cor.RequestType;

public class Lancer extends Warrior {
    private int maxHealth = 50;
    private int attack = 6;
    private int piercePower = 50;
    private int pierceThroughCount = 2;

    public Lancer() {
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

    public int getPierceThroughCount() {
        return pierceThroughCount;
    }

    public int getPiercePower() {
        return piercePower;
    }

    @Override
    public void hits(Unit enemy) {
        enemy.handle(
                new PierceHitRequest(getPierceThroughCount(),
                                     getPiercePower(),
                                     new SimpleDamage(getAttack())
                ),
                this);
        passOnToNext(() -> RequestType.HEAL, this);
    }
}
