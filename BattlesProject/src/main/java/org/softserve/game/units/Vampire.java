package org.softserve.game.units;

import org.softserve.game.Sun;
import org.softserve.game.events.Publisher;

public class Vampire extends Warrior{
    private static int MAX_HEALTH = 40;
    private static int ATTACK = 4;
    private static int VAMPIRISM = 50;

    public Vampire(){
        super(40);
    }

    @Override
    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

    public static int getVampirism() {
        return VAMPIRISM;
    }

    @Override
    public void hits(Unit enemy) {
        int initialHealth = enemy.getHealth();
        super.hits(enemy);
        this.setHealth(Math.min(MAX_HEALTH, (getHealth() + ((initialHealth - enemy.getHealth()) * getVampirism()/100))));
    }

    private void applySunStats(){
        if(Sun.getInstance().getTime().equals(Sun.DayTime.DAY)){
            MAX_HEALTH = 40;
            ATTACK = 4;
        }
        else {
            MAX_HEALTH = 50;
            ATTACK = 6;
        }
    }

    @Override
    public void update(Publisher publisher) {
        if(publisher instanceof Sun)
            applySunStats();
    }
}
