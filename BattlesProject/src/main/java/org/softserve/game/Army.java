package org.softserve.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

public class Army implements Iterable {
    ArrayList<Warrior> troops = new ArrayList<>();


    public void addUnits(Supplier<Warrior>[] factories, int[] quantities) {
        for (int i = 0; i < factories.length; i++) {
            for (int j = 0; j < quantities[i]; j++) {
                troops.add(factories[i].get());
            }
        }
    }

    boolean isDefeated() {
        return troops.isEmpty();
    }

    @Override
    public Iterator<Warrior> iterator() {
        return new FirstAliveIterator();
    }

    class FirstAliveIterator implements Iterator<Warrior> {
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            while (cursor < troops.size() && !troops.get(cursor).isAlive()) {
                cursor++;
            }
            return cursor < troops.size();
        }

        @Override
        public Warrior next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return troops.get(cursor);
        }
    }

    class WarriorIterator implements Iterator<Warrior> {
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            while (cursor < troops.size() && !troops.get(cursor).isAlive()) {
                cursor++;
            }
            return cursor < troops.size();
        }

        @Override
        public Warrior next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return troops.get(cursor++);
        }
    }
}
