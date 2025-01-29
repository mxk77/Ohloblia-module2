package com.island_simulation.entities;

import com.island_simulation.island_config.Location;

public class Duck extends Herbivore {
    public Duck(Location location){
        super(1, 200, 4, 0.15, location);
    }

    public void reproduce() {
        new Duck(this.getLocation());
    }
}
