package com.island.entities;

import com.island.config.AnimalType;
import com.island.config.Location;

public class Boar extends Herbivore {
    public Boar(Location location){
        super(400, 50, 2, 50, location);
    }

    @Override
    public void reproduce() {
        new Boar(this.getLocation());
    }

    @Override
    public AnimalType getType() {
        return AnimalType.BOAR;
    }
}
