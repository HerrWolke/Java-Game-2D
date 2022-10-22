package de.marcus.javagame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class Entity{
    protected Vector2 position;

    @JsonIgnore
    protected Texture texture;

    public Entity(float posX, float posY, Texture texture) {
        position = new Vector2(posX, posY);
        this.texture = texture;
    }


    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public void render(SpriteBatch batch, float height, float width) {
        batch.draw(texture, position.x, position.y, width, height);
    }

    public void update() {

    }

    @Override
    public String toString() {
        return "Entity{" +
                "position=" + position +
                ", texture=" + texture +
                '}';
    }
}
