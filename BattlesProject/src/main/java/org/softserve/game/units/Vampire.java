package org.softserve.game.units;

import org.softserve.game.Sun;
import org.softserve.game.events.cor.RequestType;
import org.softserve.game.events.Publisher;

public class Vampire extends Warrior{
    private int maxHealth = 40;
    private int attack = 4;
    private int vampirism = 50;

    public Vampire(){
        super(40);
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    public int getVampirism() {
        return vampirism;
    }

    @Override
    public void hits(Unit enemy) {
        int initialHealth = enemy.getHealth();
        super.hits(enemy);
        this.setHealth(Math.min(maxHealth, (getHealth() + ((initialHealth - enemy.getHealth()) * getVampirism()/100))));
        passOnToNext(() -> RequestType.HEAL, this);
    }

    private void applySunStats(){
        if(Sun.getInstance().getTime().equals(Sun.DayTime.DAY)){
            maxHealth = 40;
            attack = 4;
        }
        else {
            maxHealth = 50;
            attack = 6;
        }
    }

    @Override
    public void update(Publisher publisher) {
        if(publisher instanceof Sun)
            applySunStats();
    }
}
