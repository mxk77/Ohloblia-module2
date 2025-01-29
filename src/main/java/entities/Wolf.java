package entities;

import island.Location;

public class Wolf extends Predator {
    public Wolf(Location location) {
        super(50.0, 30, 3, 8.0, location);
    }

    public void reproduce() {
        new Wolf(this.getLocation());
    }
}
