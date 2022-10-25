package de.marcus.javagame.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.marcus.javagame.datahandling.Loadable;
import de.marcus.javagame.entities.Creature;
import de.marcus.javagame.entities.Entity;
import de.marcus.javagame.entities.Player;
import de.marcus.javagame.entities.Weapon;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.UUID;

/**
 * @author Marcus
 * <p>
 * Entity System: Manager
 */

@Getter
@Setter

public class EntityManager extends Loadable {
    @JsonProperty("active_entities")
    private LinkedHashMap<UUID, Entity> currentUsedEntities;

    @JsonProperty("loaded_entities")
    private LinkedHashMap<UUID, Entity> memoryLoadedEntities;

    @JsonIgnore
    private final Player player;

    @JsonIgnore
    private float passedAnimTime;


    public EntityManager() {
        passedAnimTime = 0f;
        this.currentUsedEntities = new LinkedHashMap<>();
        this.memoryLoadedEntities = new LinkedHashMap<>();
        this.player = new Player(60, 80);
    }


    public void render(@NonNull SpriteBatch spriteBatch) {
        //needed for animations to correctly work
        passedAnimTime += Gdx.graphics.getDeltaTime();

        spriteBatch.begin();
        player.render(spriteBatch, passedAnimTime, 2.5f, 2.5f);
        for (Entity entity : currentUsedEntities.values()) {
            spriteBatch.draw(entity.getTexture(), entity.getPosition().x, entity.getPosition().y);
        }
        spriteBatch.end();
    }

    public void update() {
        for (Entity entity : currentUsedEntities.values()) {
            entity.update();
        }
    }

    public void moveToMemory(@NonNull UUID @NotNull ... uuids) {
        HashMap<UUID, Entity> toMove = new HashMap<>();

        for (UUID uuid : uuids) {
            toMove.put(uuid, currentUsedEntities.get(uuid));
            currentUsedEntities.remove(uuid);
        }
        memoryLoadedEntities.putAll(toMove);

    }

    public void spawn(@NonNull Entity... entities) {
        for (Entity entity : entities) {
            currentUsedEntities.put(UUID.randomUUID(), entity);
        }
    }

    public void despawn(@NonNull UUID... uuids) {
        for (UUID uuid : uuids) {
            currentUsedEntities.remove(uuid);
        }
    }

    public void kill(@NonNull UUID uuid, Weapon killer) {

        try {
            Creature creature = (Creature) currentUsedEntities.get(uuid);
            creature.die(killer);
            currentUsedEntities.remove(uuid);
        } catch (ClassCastException ex) {
            System.err.println("Fatal Error. An Entity which is not a creature was called to be killed. UUID " + uuid);
        }

    }

    @Override
    public String toString() {
        return "EntityManager{" +
                "currentUsedEntities=" + currentUsedEntities.toString() +
                ", memoryLoadedEntities=" + memoryLoadedEntities.toString() +
                '}';
    }
}