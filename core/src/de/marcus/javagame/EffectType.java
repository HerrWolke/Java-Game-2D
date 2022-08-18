package de.marcus.javagame;

public enum EffectType {
    POISON(EffectInfluence.DAMAGE),
    FIRE(EffectInfluence.DAMAGE),
    FROZEN(EffectInfluence.MOVEMENT);

    EffectType(EffectInfluence influence) {
        this.influence = influence;
    }

    private EffectInfluence influence;

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
