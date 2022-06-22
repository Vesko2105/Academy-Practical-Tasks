package org.softserve.game;

import java.util.function.Supplier;

public class Knight extends Warrior implements hasAttack{
    private static int ATTACK = 7;

    @Override
    public int getAttack(){
        return ATTACK;
    }
}
