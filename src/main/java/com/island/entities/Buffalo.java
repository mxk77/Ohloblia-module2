package com.island.entities;

import com.island.config.AnimalType;
import com.island.config.Location;

public class Buffalo extends Herbivore {
    public Buffalo(Location location){
        super(700, 10, 3, 100, location);
    }

    @Override
    public void reproduce() {
        new Buffalo(this.getLocation());
    }

    @Override
    public AnimalType getType() {
        return AnimalType.BUFFALO;
    }
}
