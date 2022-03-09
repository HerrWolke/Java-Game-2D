package de.marcus.javagame.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.marcus.javagame.entities.Entity;

import java.util.ArrayList;

public class EntityManager {
    public ArrayList<Entity> entities;

    public EntityManager() {
        entities = new ArrayList<>();
    }

    public void render(SpriteBatch spriteBatch) {
        for (Entity entity : entities) {
            spriteBatch.draw(entity.getTexture(),entity.getPosition().x,entity.getPosition().y);
        }
    }

    public void update() {
        for (Entity entity : entities) {
            entity.update();
        }
    }
}
