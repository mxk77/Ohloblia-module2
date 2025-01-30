package com.island.entities;

import com.island.config.AnimalType;
import com.island.config.Location;

public class Eagle extends Predator {
    public Eagle(Location location){
        super(6, 20, 3, 1, location);
    }

    @Override
    public void reproduce() {
        new Eagle(this.getLocation());
    }

    @Override
    public AnimalType getType() {
        return AnimalType.EAGLE;
    }
}
