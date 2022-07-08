package org.softserve.game.units;

import org.softserve.game.events.cor.Handler;
import org.softserve.game.events.cor.Request;
import org.softserve.game.events.cor.RequestType;

public class Healer extends Unit{
    private int maxHealth = 60;
    private int healingPower = 2;
    private int maxMedkits = 20;
    private int medkits = 20;
    private int healRegenerationCooldown = 3;

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    public Healer(){
        super(60);
    }

    private void heal(Unit unitToHeal){
        if(!isAlive())
            return;

        if(medkits < maxMedkits)
            healRegenerationCooldown--;

        if(healRegenerationCooldown == 0){
            medkits = Math.min(maxMedkits, medkits + 1);
            healRegenerationCooldown = 3;
        }

        if(medkits > 0 && unitToHeal.getHealth() < unitToHeal.getMaxHealth()){
            unitToHeal.setHealth(Math.min(unitToHeal.getMaxHealth(), unitToHeal.getHealth() + healingPower));
            medkits--;
        }
    }

    @Override
    public void handle(Request request, Handler sender) {
        if(request.getType() == RequestType.HEAL){
            heal((Unit) sender);
            passOnToNext(request, this);
            return;
        }
        super.handle(request, sender);
    }
}
