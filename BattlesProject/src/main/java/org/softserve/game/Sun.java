package org.softserve.game;

import org.softserve.game.events.BasePublisher;

public final class Sun extends BasePublisher {
    private static Sun instance;
    private DayTime time;

    public enum DayTime{
        DAY("Day"), NIGHT("Night");

        DayTime(String time) {
        }
    }

    private Sun(DayTime time) {
        this.time = time;
    }

    public static Sun getInstance(){
        if (instance == null){
            instance = new Sun(DayTime.DAY);
        }
        return instance;
    }

    public void setTime(DayTime time){
        this.time = time;
        notifySubscribers();
    }

     public DayTime getTime(){
        return time;
     }
}
