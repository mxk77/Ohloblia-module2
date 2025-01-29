package com.island.entities;

import com.island.config.AnimalType;
import com.island.config.Location;

public class Bear extends Predator {
    public Bear(Location location){
        super(500,5,2,80, location);
    }

    @Override
    public void reproduce() {
        new Bear(this.getLocation());
    }

    @Override
    public AnimalType getType() {
        return AnimalType.BEAR;
    }
}
