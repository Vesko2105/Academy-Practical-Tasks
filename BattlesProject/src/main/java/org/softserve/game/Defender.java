package org.softserve.game;

public class Defender extends Warrior implements hasAttack{
    private static int ATTACK = 3;
    private static int DEFENSE = 2;

    @Override
    public int getAttack() {
        return ATTACK;
    }

    public static int getDefence() {
        return DEFENSE;
    }

    @Override
    protected void hitBy(hasAttack attacker) {
        super.hitBy(() -> Math.max(0, attacker.getAttack() - getDefence()));
    }
}
