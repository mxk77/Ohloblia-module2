package com.island_simulation.entities;

import com.island_simulation.island_config.Location;

public class Bear extends Predator {
    public Bear(Location location){
        super(500,5,2,80, location);
    }

    public void reproduce() {
        new Bear(this.getLocation());
    }
}
