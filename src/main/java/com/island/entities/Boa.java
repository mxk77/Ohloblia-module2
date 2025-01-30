package com.island.entities;

import com.island.config.AnimalType;
import com.island.config.Location;

public class Boa extends Predator {
    public Boa(Location location) {
        super(15.0, 30, 1, 3, location);
    }

    @Override
    public void reproduce() {
        new Boa(this.getLocation());
    }

    @Override
    public AnimalType getType() {
        return AnimalType.BOA;
    }
}
