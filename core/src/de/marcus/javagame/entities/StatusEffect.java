package de.marcus.javagame.entities;

import de.marcus.javagame.EffectType;

public class StatusEffect {
    private EffectType effectType;
    //ms
    private long duration;

    public StatusEffect(EffectType effectType, long duration) {
        this.effectType = effectType;
        this.duration = duration;
    }

    public boolean decrementTimer(long decrement) {
        duration -= decrement;

        return duration > 0;
    }
}
