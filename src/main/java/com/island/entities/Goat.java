package com.island.entities;

import com.island.config.Location;

public class Goat extends Herbivore {
    public Goat(Location location){
        super(60, 140, 3, 10, location);
    }

    public void reproduce() {
        new Goat(this.getLocation());
    }
}
