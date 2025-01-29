package entities;

import island.Location;

public class Caterpillar extends Herbivore {
    public Caterpillar(Location location){
        super(0.01, 1000, 0, 0, location);
    }

    public void reproduce() {
        new Caterpillar(this.getLocation());
    }
}
