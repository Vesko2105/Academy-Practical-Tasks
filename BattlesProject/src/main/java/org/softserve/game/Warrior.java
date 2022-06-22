package org.softserve.game;

public class Warrior implements hasAttack{
    private static int ATTACK = 5;
    private int health = 50;

    int getHealth() {
        return health;
    }

    private void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return ATTACK;
    }

    boolean isAlive() {
        return health > 0;
    }

    public void hits(Warrior defender) {
        defender.hitBy(this);
    }

    protected void hitBy(hasAttack attacker) {
        setHealth(getHealth() - attacker.getAttack());
    }
}
