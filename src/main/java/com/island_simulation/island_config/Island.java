package com.island_simulation.island_config;

import java.util.ArrayList;
import java.util.List;

public class Island {
    private final List<List<Location>> map = new ArrayList<>();

    private static final int WIDTH = 100;
    private static final int HEIGHT = 20;

    public Island() {
        for (int x = 0; x < WIDTH; x++) {
            List<Location> column = new ArrayList<>();
            for (int y = 0; y < HEIGHT; y++) {
                column.add(new Location(x, y));
            }
            map.add(column);
        }
    }

    public Location getLocation(int x, int y) {
        return map.get(x).get(y);
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }
}
