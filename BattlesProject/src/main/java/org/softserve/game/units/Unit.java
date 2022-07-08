package org.softserve.game.units;

import org.softserve.game.damage.Damage;
import org.softserve.game.damage.SimpleDamage;
import org.softserve.game.events.cor.*;
import org.softserve.game.events.Publisher;
import org.softserve.game.events.Subscriber;

public class Unit implements Handler, Subscriber {
    private int maxHealth = 50;
    private int health;
    private Handler nextHandler;

    public Unit() {
        this(50);
    }

    protected Unit(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    protected void setHealth(int health) {
        this.health = health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void hits(Unit enemy) {
        //TO-DO (For example: throw new UnsupportedOperationException();)
    }

    protected void hitBy(Damage damage) {
        setHealth(getHealth() - damage.getDamage());
    }

    @Override
    public void setNext(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handle(Request request, Handler sender) {
        if (request != null) {
            switch (request.getType()) {
                case RECEIVE_DMG:
                    DamageRequest damageRequest = (DamageRequest) request;
                    hitBy(damageRequest.getDamage());
                    break;
                case PIERCE_HIT:
                    PierceHitRequest pierceHitRequest = (PierceHitRequest) request;
                    int healthBefore = getHealth();
                    hitBy(pierceHitRequest.getDamage());
                    if(!pierceHitRequest.isAtLast()) {
                        int healthAfter = Math.max(0, getHealth());
                        Damage damageReceived = new SimpleDamage(healthBefore - healthAfter);
                        passOnToNext(pierceHitRequest.goThrough(damageReceived), sender);
                    }
                    break;
                case HEAL:
                    if (isAlive())
                        passOnToNext(() -> RequestType.HEAL, this);
                    else
                        passOnToNext(() -> RequestType.HEAL, sender);
                    break;
                default:
                    passOnToNext(request, this);
            }
        }
    }

    public void passOnToNext(Request request, Handler sender) {
        if (nextHandler != null)
            nextHandler.handle(request, sender);
    }

    public Handler getNext() {
        return nextHandler;
    }

    @Override
    public void update(Publisher publisher) {
        //TO-DO
    }
}
