package org.softserve.game.events.cor;

import org.softserve.game.damage.Damage;
import org.softserve.game.damage.SimpleDamage;

public class PierceHitRequest implements Request {
    private int pierceThroughCount;
    private int piercePower;
    private Damage damage;
    private int receiverAt = 1;

    public PierceHitRequest(int pierceThroughCount, int piercePower, Damage damage) {
        this.pierceThroughCount = pierceThroughCount;
        this.piercePower = piercePower;
        this.damage = damage;
    }

    public PierceHitRequest goThrough(Damage dealtDamage) {
        damage = new SimpleDamage(dealtDamage.getDamage() * piercePower / 100);
        receiverAt++;
        return this;
    }

    public boolean isAtLast() {
        return receiverAt == pierceThroughCount;
    }

    @Override
    public RequestType getType() {
        return RequestType.PIERCE_HIT;
    }

    public Damage getDamage() {
        return damage;
    }
}
