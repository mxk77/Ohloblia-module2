package com.island.entities;

import com.island.config.AnimalType;
import com.island.config.Location;

public class Fox extends Predator {
    public Fox(Location location) {
        super(8, 30, 2, 2, location);
    }

    @Override
    public void reproduce() {
        new Fox(this.getLocation());
    }

    @Override
    public AnimalType getType() {
        return AnimalType.FOX;
    }
}
