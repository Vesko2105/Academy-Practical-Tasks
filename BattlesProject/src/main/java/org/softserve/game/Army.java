package org.softserve.game;

import org.softserve.game.units.Unit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

public class Army implements Iterable<Unit> {
    ArrayList<Unit> troops = new ArrayList<>();


    public void addUnits(Supplier<Unit>[] factories, int[] quantities) {
        for (int i = 0; i < factories.length; i++) {
            for (int j = 0; j < quantities[i]; j++) {
                Unit unit = factories[i].get();
                if (!troops.isEmpty()) {
                    troops.get(troops.size() - 1).setNext(unit);
                }
                troops.add(unit);
            }
        }
    }

    public void addUnits(Supplier<Unit> factory, int quantity) {
        for (int j = 0; j < quantity; j++) {
            Unit unit = factory.get();
            if (!troops.isEmpty()) {
                troops.get(troops.size() - 1).setNext(unit);
            }
            troops.add(unit);
        }
    }

    public void lineUpInRow() {
        troops.forEach(unit -> unit.setNext(null));
    }

    public void lineUpInColumn(){
        int index = 0;
        while ((index + 1) < troops.size()) {
            Unit unit = troops.get(index);
            Unit unitBehind = troops.get(++index);
            unit.setNext(unitBehind);
        }
    }

    boolean isDefeated() {
        return troops.stream().filter(Unit::isAlive).findAny().orElse(null) == null;
    }

    @Override
    public Iterator<Unit> iterator() {
        return new UnitIterator();
    }

    public Iterator<Unit> firstAliveIterator() {
        return new FirstAliveIterator();
    }

    class FirstAliveIterator implements Iterator<Unit> {
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            while (cursor < troops.size() && !troops.get(cursor).isAlive()) {
                cursor++;
            }
            return cursor < troops.size();
        }

        @Override
        public Unit next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return troops.get(cursor);
        }
    }

    class UnitIterator implements Iterator<Unit> {
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            while (cursor < troops.size() && !troops.get(cursor).isAlive()) {
                cursor++;
            }
            return cursor < troops.size();
        }

        @Override
        public Unit next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return troops.get(cursor++);
        }
    }
}
