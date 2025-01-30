package com.island.entities;

import com.island.config.AnimalType;
import com.island.config.Location;

public class Deer extends Herbivore {
    public Deer(Location location){
        super(300, 20, 4, 50, location);
    }

    @Override
    public void reproduce() {
        new Deer(this.getLocation());
    }

    @Override
    public AnimalType getType() {
        return AnimalType.DEER;
    }
}
