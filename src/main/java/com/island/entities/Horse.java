package com.island.entities;

import com.island.config.AnimalType;
import com.island.config.Location;

public class Horse extends Herbivore {
    public Horse(Location location){
        super(400, 20, 4, 60, location);
    }

    @Override
    public void reproduce() {
        new Horse(this.getLocation());
    }

    @Override
    public AnimalType getType() {
        return AnimalType.HORSE;
    }
}
