package com.island.entities;

import com.island.config.Location;

public class Deer extends Herbivore {
    public Deer(Location location){
        super(300, 20, 4, 50, location);
    }

    public void reproduce() {
        new Deer(this.getLocation());
    }
}
