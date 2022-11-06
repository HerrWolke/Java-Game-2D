package de.marcus.javagame.framework.collisions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BodyData {
    private boolean canBeDestroyed;
    private boolean markedForDestruction;

    private CollisionType collisionType;


    private String collisionName;


    public BodyData(boolean canBeDestroyed, boolean markedForDestruction, CollisionType type) {
        this.canBeDestroyed = canBeDestroyed;
        this.markedForDestruction = markedForDestruction;
        this.collisionType = type;
    }

    public BodyData(boolean canBeDestroyed, boolean markedForDestruction, CollisionType type, String collisionName) {
        this.canBeDestroyed = canBeDestroyed;
        this.markedForDestruction = markedForDestruction;
        this.collisionType = type;
        this.collisionName = collisionName;
    }

    public enum CollisionType {
        NPC, PLAYER, ENEMY, WALL
    }
}
