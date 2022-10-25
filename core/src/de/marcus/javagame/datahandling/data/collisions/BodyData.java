package de.marcus.javagame.datahandling.data.collisions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BodyData {
    private boolean canBeDestroyed;
    private boolean markedForDestruction;

    public BodyData(boolean canBeDestroyed, boolean markedForDestruction) {
        this.canBeDestroyed = canBeDestroyed;
        this.markedForDestruction = markedForDestruction;
    }
}
