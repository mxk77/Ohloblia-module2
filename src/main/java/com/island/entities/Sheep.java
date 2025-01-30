package com.island.entities;

import com.island.config.AnimalType;
import com.island.config.Location;

public class Sheep extends Herbivore {
    public Sheep(Location location){
        super(70, 140, 3, 15, location);
    }

    @Override
    public void reproduce() {
        new Sheep(this.getLocation());
    }

    @Override
    public AnimalType getType() {
        return AnimalType.SHEEP;
    }
}
