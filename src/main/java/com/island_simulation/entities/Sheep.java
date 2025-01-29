package com.island_simulation.entities;

import com.island_simulation.island_config.Location;

public class Sheep extends Herbivore {
    public Sheep(Location location){
        super(70, 140, 3, 15, location);
    }

    public void reproduce() {
        new Sheep(this.getLocation());
    }
}
