package com.island.entities;

import com.island.config.AnimalType;
import com.island.config.Location;

public class Caterpillar extends Herbivore {
    public Caterpillar(Location location){
        super(0.01, 1000, 0, 0, location);
    }

    @Override
    public void reproduce() {
        new Caterpillar(this.getLocation());
    }

    @Override
    public AnimalType getType() {
        return AnimalType.CATERPILLAR;
    }
}
