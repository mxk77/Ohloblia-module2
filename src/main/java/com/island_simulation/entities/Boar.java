package com.island_simulation.entities;

import com.island_simulation.island_config.Location;

public class Boar extends Herbivore {
    public Boar(Location location){
        super(400, 50, 2, 50, location);
    }

    public void reproduce() {
        new Boar(this.getLocation());
    }
}
