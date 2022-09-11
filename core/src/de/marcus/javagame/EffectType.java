package de.marcus.javagame;

import lombok.Getter;

@Getter
public enum EffectType {
    POISON(EffectInfluence.DAMAGE, 0.5,4),
    FIRE(EffectInfluence.DAMAGE, 1.0,4),
    FROZEN(EffectInfluence.MOVEMENT, 0.0,4),
    HEAL(EffectInfluence.DAMAGE,1.0,0.5);

    EffectType(EffectInfluence influence, double damage,double applyTime) {
        this.influence = influence;
        this.damage = damage;
        this.applyTime = applyTime;
    }

    private final EffectInfluence influence;
    private final double damage;

    private final double applyTime;

    private enum EffectInfluence {
        /**
         * These Effects can be positive or negative
         * <p>
         * In case of negative, they would p.e. slow the player or damage him
         */
        DAMAGE,
        MOVEMENT
    }
}
