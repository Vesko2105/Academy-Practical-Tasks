package org.softserve.game.events;

public interface Publisher {
    void subscribe(Subscriber newSubscriber);
    void unsubscribe(Subscriber subscriber);
    void notifySubscribers();
}
