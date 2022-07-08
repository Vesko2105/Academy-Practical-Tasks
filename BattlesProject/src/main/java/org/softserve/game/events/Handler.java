package org.softserve.game.events;

public interface Handler {
    void setNext(Handler nextHandler);
    void handle(Request request, Handler sender);
}
