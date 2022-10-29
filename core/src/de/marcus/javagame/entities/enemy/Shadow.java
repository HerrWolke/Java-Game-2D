package de.marcus.javagame.entities.enemy;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.marcus.javagame.entities.Creature;
import de.marcus.javagame.managers.TextureManager;

import java.util.Arrays;
import java.util.List;

public class Shadow extends Creature {
    public Shadow(List<Animation<TextureRegion>> animations) {
        super(0, 0, null, 4, 4, 4, 4, 8.0f, Arrays.asList(
                TextureManager.getCharacterAnimation("standingback", true, 0.2f),
                TextureManager.getCharacterAnimation("walkback", true, 0.2f),
                TextureManager.getCharacterAnimation("walkfront", true, 0.2f),
                TextureManager.getCharacterAnimation("walkright", true, 0.2f)
        ));
    }
}
