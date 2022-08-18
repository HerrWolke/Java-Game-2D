package de.marcus.javagame.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.marcus.javagame.entities.Creature;
import de.marcus.javagame.entities.Entity;
import de.marcus.javagame.entities.Weapon;

import java.util.LinkedHashMap;
import java.util.UUID;

/**
 * @author Marcus
 * <p>
 * Entity System: Manager
 */
public class EntityManager {
    private LinkedHashMap<UUID, Entity> currentUsedEntites;

    public EntityManager() {
        this.currentUsedEntites = new LinkedHashMap<>();
    }

    public void render(SpriteBatch spriteBatch) {
        for (Entity entity : currentUsedEntites.values()) {
            spriteBatch.draw(entity.getTexture(), entity.getPosition().x, entity.getPosition().y);
        }
    }

    public void update() {
        for (Entity entity : currentUsedEntites.values()) {
            entity.update();
        }
    }

    public void load() {

    }

    public void spawn(Entity... entities) {
        for (Entity entity : entities) {
            currentUsedEntites.put(UUID.randomUUID(), entity);
        }

    }

    public void despawn(UUID... uuids) {
        for (UUID uuid : uuids) {
            currentUsedEntites.remove(uuid);
        }
    }

    public void kill(UUID uuid, Weapon killer) {

            try {
                Creature creature = (Creature) currentUsedEntites.get(uuid);
                creature.die(killer);
                currentUsedEntites.remove(uuid);
            } catch (ClassCastException ex) {
                System.err.println("Fatal Error. An Entity which is not a creature was called to be killed. UUID " + uuid);
            }

        }
    }
