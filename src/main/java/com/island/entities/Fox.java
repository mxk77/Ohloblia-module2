package com.island.entities;

import com.island.config.Location;

public class Fox extends Predator {
    public Fox(Location location) {
        super(8, 30, 2, 2, location);
    }

    public void reproduce() {
        new Fox(this.getLocation());
    }
}
