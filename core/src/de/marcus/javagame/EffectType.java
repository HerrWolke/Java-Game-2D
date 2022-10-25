package de.marcus.javagame;

import lombok.Getter;

@Getter
public enum EffectType {
    POISON(EffectInfluence.HEALTH, 0.5, 4),
    FIRE(EffectInfluence.HEALTH, 1.0, 4),
    FROZEN(EffectInfluence.MOVEMENT, 0.0, 4),
    HEAL(EffectInfluence.HEALTH, 1.0, 0.5),
    STRENGTH(EffectInfluence.DAMAGE, 1.0, 1),
    SPEED(EffectInfluence.MOVEMENT, 1.0, 5);

    EffectType(EffectInfluence influence, double influenceStrength, double applyTime) {
        this.influence = influence;
        this.damage = influenceStrength;
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
        HEALTH,
        MOVEMENT,
        DAMAGE
    }
}
