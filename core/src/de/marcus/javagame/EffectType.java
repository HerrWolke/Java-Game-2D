package de.marcus.javagame;

public enum EffectType {
    POISON(EffectInfluence.DAMAGE,0.5),
    FIRE(EffectInfluence.DAMAGE,1.0),
    FROZEN(EffectInfluence.MOVEMENT,0.0);

    EffectType(EffectInfluence influence, double damage) {
        this.influence = influence;
        this.damage = damage;
    }

    private EffectInfluence influence;
    private double damage;

    private enum EffectInfluence {
        /**
         * These Effects can be positive or negative
         *
         * In case of negative, they would p.e. slow the player or damage him
         */
        DAMAGE,
        MOVEMENT
    }
}
