package de.marcus.javagame.misc;

import de.alsclo.voronoi.graph.Point;

import java.util.Comparator;

public class CustomComparator implements Comparator<Point> {

    @Override
    public int compare(Point o1, Point o2) {

        int comp = Double.compare(Math.round(o1.x), Math.round(o2.x));
        if(comp == 0) {
            comp = Double.compare(Math.round(o1.y), Math.round(o2.y));
        }
        return comp;
    }
}