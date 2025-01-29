package com.island_simulation.entities;

import com.island_simulation.island_config.Location;

public class Fox extends Predator {
    public Fox(Location location) {
        super(8, 30, 2, 2, location);
    }

    public void reproduce() {
        new Fox(this.getLocation());
    }
}
