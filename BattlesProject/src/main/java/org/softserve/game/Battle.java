package org.softserve.game;

public class Battle {
    public static boolean fight(
        Warrior firstWarrior,
        Warrior secondWarrior
    ) {
        while (firstWarrior.isAlive() && secondWarrior.isAlive()){
            firstWarrior.hits(secondWarrior);
            if(secondWarrior.isAlive())
                secondWarrior.hits(firstWarrior);
        }
        return firstWarrior.isAlive();
    }

    public static boolean fight(
            Army attackingArmy,
            Army defendingArmy
    ) {
        var it1 = attackingArmy.iterator();
        var it2 = defendingArmy.iterator();
        while(it1.hasNext() && it2.hasNext()){
            fight(it1.next(), it2.next());
        }

        return it1.hasNext();
    }
}
