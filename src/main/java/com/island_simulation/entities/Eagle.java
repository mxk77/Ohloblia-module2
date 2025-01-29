package com.island_simulation.entities;

import com.island_simulation.island_config.Location;

public class Eagle extends Predator {
    public Eagle(Location location){
        super(6, 20, 3, 1, location);
    }

    public void reproduce() {
        new Eagle(this.getLocation());
    }
}
