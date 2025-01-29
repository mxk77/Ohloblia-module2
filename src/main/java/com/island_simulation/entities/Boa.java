package com.island_simulation.entities;

import com.island_simulation.island_config.Location;

public class Boa extends Predator {
    public Boa(Location location) {
        super(15.0, 30, 1, 3, location);
    }

    public void reproduce() {
        new Boa(this.getLocation());
    }
}
