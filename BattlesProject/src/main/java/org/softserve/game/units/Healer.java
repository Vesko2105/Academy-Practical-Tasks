package org.softserve.game.units;

import org.softserve.game.events.Handler;
import org.softserve.game.events.Request;

public class Healer extends Unit{
    private static int MAX_HEALTH = 60;
    private static int HEALING_POWER = 2;
    private int MAX_MEDKITS = 20;
    private int medKits = 20;
    private int healRegenerationCooldown = 3;

    public Healer(){
        super(60);
    }

    private void heal(Unit unitToHeal){
        if(!isAlive())
            return;

        if(medKits < MAX_MEDKITS)
            healRegenerationCooldown--;

        if(healRegenerationCooldown == 0){
            medKits = Math.min(MAX_MEDKITS, medKits + 1);
            healRegenerationCooldown = 3;
        }

        if(medKits > 0 && unitToHeal.getHealth() < unitToHeal.getMaxHealth()){
            unitToHeal.setHealth(Math.min(unitToHeal.getMaxHealth(), unitToHeal.getHealth() + HEALING_POWER));
            medKits--;
        }
    }

    @Override
    public void handle(Request request, Handler sender) {
        if(canHandle(request))
            heal((Unit) sender);
        super.handle(request, sender);
    }

    @Override
    protected boolean canHandle(Request request) {
        return request == Request.HEAL;
    }
}
