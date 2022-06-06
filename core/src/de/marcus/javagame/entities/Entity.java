package de.marcus.javagame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Marcus
 * <p>
 * Entity System: Entity
 * <p>
 * Intended for non-living entities (items).
 * Base class for Creature
 * @see Creature
 */
@Getter
@Setter
@AllArgsConstructor
public class Entity {
    private Vector2 position;
    private Texture texture;

    public Entity(float posX, float posY, Texture texture) {
        position = new Vector2(posX, posY);
        this.texture = texture;
    }


    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public void update() {

    }
}
