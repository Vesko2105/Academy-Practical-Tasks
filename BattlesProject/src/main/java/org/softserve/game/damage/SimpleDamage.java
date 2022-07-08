package org.softserve.game.damage;

public class SimpleDamage implements Damage {
    int damage;

    public SimpleDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public int getDamage() {
        return damage;
    }
}