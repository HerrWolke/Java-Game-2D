package de.marcus.javagame.entities;

import com.badlogic.gdx.graphics.Texture;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Entity {
    private float posX;
    private float posY;
    private Texture texture;
}
