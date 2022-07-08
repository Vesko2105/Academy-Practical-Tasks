package org.softserve.game.units;

import org.softserve.game.damage.Damage;
import org.softserve.game.damage.SimpleDamage;

public class Lancer extends Warrior{
    private static int MAX_HEALTH = 50;
    private static int ATTACK = 6;
    private static int PIERCE_POWER = 50;

    public Lancer(){
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

    @Override
    public void hits(Unit enemy) {
        int initialHealth = enemy.getHealth();
        super.hits(enemy);
        int healthAfter = enemy.getHealth();

        Damage dmg = new SimpleDamage((initialHealth - healthAfter)*PIERCE_POWER/100);

        Unit secondUnit = (Unit)enemy.getNext();
        if(secondUnit != null)
            secondUnit.hitBy(dmg);
    }
}
