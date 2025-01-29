package com.island.entities;

import com.island.config.Location;

public class Rabbit extends Herbivore {
    public Rabbit(Location location){
        super(2, 150, 2, 0.45, location);
    }

    public void reproduce() {
        new Rabbit(this.getLocation());
    }
}
