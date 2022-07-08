package org.softserve.game.units;

import org.softserve.game.damage.Damage;
import org.softserve.game.events.Handler;
import org.softserve.game.events.Publisher;
import org.softserve.game.events.Request;
import org.softserve.game.events.Subscriber;

public class Unit implements Handler, Subscriber {
    private static int MAX_HEALTH = 50;
    private int health;
    private  Handler nextHandler;

    public Unit(){
        this(50);
    }

    protected Unit(int health){
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth(){
        return MAX_HEALTH;
    }

    protected void setHealth(int health) {
        this.health = health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void hits(Unit enemy){
        //throw new UnsupportedOperationException();
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
        if(request != null && nextHandler != null)
        {
            if(request.equals(Request.HEAL) && !isAlive()){
                nextHandler.handle(request, sender);
                return;
            }
            nextHandler.handle(request, this);
        }
    }

    public Handler getNext(){
        return nextHandler;
    }

    protected boolean canHandle(Request request){
        return false;
    }

    @Override
    public void update(Publisher publisher) {

    }
}
