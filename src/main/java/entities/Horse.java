package entities;

import island.Location;

public class Horse extends Herbivore {
    public Horse(Location location){
        super(400, 20, 4, 60, location);
    }

    public void reproduce() {
        new Horse(this.getLocation());
    }
}
