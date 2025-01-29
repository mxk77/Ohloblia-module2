package com.island.entities;

import com.island.config.Location;

public class Duck extends Herbivore {
    public Duck(Location location){
        super(1, 200, 4, 0.15, location);
    }

    public void reproduce() {
        new Duck(this.getLocation());
    }
}
