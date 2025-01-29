package com.island.entities;

import com.island.config.Location;

public class Mouse extends Herbivore {
    public Mouse(Location location){
        super(0.05, 500, 1, 0.01, location);
    }

    public void reproduce() {
        new Mouse(this.getLocation());
    }
}
