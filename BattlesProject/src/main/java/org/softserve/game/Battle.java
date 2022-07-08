package org.softserve.game;

import org.softserve.game.events.BasePublisher;
import org.softserve.game.events.Publisher;
import org.softserve.game.events.Subscriber;
import org.softserve.game.units.Unit;

import java.util.ArrayList;
import java.util.List;

public class Battle extends BasePublisher {
    private List<Subscriber> subscribers = new ArrayList<>();

    public static boolean fight(
            Unit firstUnit,
            Unit secondUnit
    ) {
        while (firstUnit.isAlive() && secondUnit.isAlive()) {
            firstUnit.hits(secondUnit);
            if (secondUnit.isAlive())
                secondUnit.hits(firstUnit);
        }
        return firstUnit.isAlive();
    }

    public static boolean fight(
            Army attackingArmy,
            Army defendingArmy
    ) {
        var it1 = attackingArmy.firstAliveIterator();
        var it2 = defendingArmy.firstAliveIterator();
        while (it1.hasNext() && it2.hasNext()) {
            fight(it1.next(), it2.next());
        }

        return it1.hasNext();
    }

    public static boolean straightFight(Army attackers, Army enemies) {
        attackers.lineUpInRow();
        enemies.lineUpInRow();

        while (true) {
            var it1 = attackers.iterator();
            if(!it1.hasNext())
                return false;
            var it2 = enemies.iterator();
            if(!it2.hasNext())
                return true;
            while(it1.hasNext() && it2.hasNext())
                fight(it1.next(), it2.next());
        }
    }

    @Override
    public void subscribe(Subscriber newSubscriber) {
        subscribers.add(newSubscriber);
    }

    public void subscribeAll(Subscriber... subscribers){
        for(Subscriber s : subscribers){
            subscribe(s);
        }
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers() {
        for (Subscriber s : subscribers){
            s.update(this);
        }
    }
}
