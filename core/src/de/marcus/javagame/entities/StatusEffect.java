package de.marcus.javagame.entities;

import de.marcus.javagame.EffectType;

public class StatusEffect {
    private EffectType effectType;
    //ms
    private long duration;
    private long nextCallTime;


    public StatusEffect(EffectType effectType, long duration) {
        this.effectType = effectType;
        this.duration = duration;
        System.out.println((effectType.getApplyTime() * 1000));
        nextCallTime = (long) (duration - (effectType.getApplyTime() * 1000));
        System.out.println("next call time " + nextCallTime);
    }

    public void update(Creature creature, long deltaTime) {
        decrementTimer(deltaTime, creature);
    }

    public boolean decrementTimer(long decrement,Creature creature) {
        duration -= decrement;
        //The next call time is the duration divided into segments by ApplyTime of the given effect
        //TODO: Maybe don't keep duration > 0 but idk yet
        if(duration <= nextCallTime && duration > 0) {
            if(creature.getHealth() + (int) effectType.getDamage() < creature.getMaxHealth() ) {
                creature.setHealth(creature.getHealth() + (int) effectType.getDamage());
                // apply time is in seconds, nextCallTime in ms
                nextCallTime -= effectType.getApplyTime() * 1000;
            } else {
                creature.setHealth(creature.getMaxHealth());
            }
        }

        return duration < 0;
    }
}
