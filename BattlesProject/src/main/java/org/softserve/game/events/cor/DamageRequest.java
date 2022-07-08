package org.softserve.game.events.cor;

import org.softserve.game.damage.Damage;

public class DamageRequest implements Request {
    Damage damage;

    public DamageRequest(Damage damage) {
        this.damage = damage;
    }

    public Damage getDamage() {
        return damage;
    }

    @Override
    public RequestType getType() {
        return RequestType.RECEIVE_DMG;
    }
}
