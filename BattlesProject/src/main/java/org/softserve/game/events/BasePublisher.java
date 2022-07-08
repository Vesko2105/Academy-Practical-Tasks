package org.softserve.game.events;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePublisher implements Publisher{
    private List<Subscriber> subscribers = new ArrayList<>();

    @Override
    public void subscribe(Subscriber newSubscriber) {
        subscribers.add(newSubscriber);
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
