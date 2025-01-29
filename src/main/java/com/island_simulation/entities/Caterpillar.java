package com.island_simulation.entities;

import com.island_simulation.island_config.Location;

public class Caterpillar extends Herbivore {
    public Caterpillar(Location location){
        super(0.01, 1000, 0, 0, location);
    }

    public void reproduce() {
        new Caterpillar(this.getLocation());
    }
}
