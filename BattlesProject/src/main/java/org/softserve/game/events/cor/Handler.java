package org.softserve.game.events.cor;

public interface Handler {
    void setNext(Handler nextHandler);
    void handle(Request request, Handler sender);
}
