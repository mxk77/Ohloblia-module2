package com.island.entities;

import com.island.config.AnimalType;
import com.island.config.Location;

public class Wolf extends Predator {
    public Wolf(Location location) {
        super(50.0, 30, 3, 8.0, location);
    }

    @Override
    public void reproduce() {
        new Wolf(this.getLocation());
    }

    @Override
    public AnimalType getType() {
        return AnimalType.WOLF;
    }
}
