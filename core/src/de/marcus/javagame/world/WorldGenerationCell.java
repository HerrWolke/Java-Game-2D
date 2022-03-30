package de.marcus.javagame.world;

import de.alsclo.voronoi.graph.Point;

public class WorldGenerationCell {
    private Point point;
    private boolean isLeader;
    private BiomeType biomeType;

    public WorldGenerationCell(Point point, boolean isLeader, BiomeType biomeType) {
        this.point = point;
        this.isLeader = isLeader;
        this.biomeType = biomeType;
    }


}
