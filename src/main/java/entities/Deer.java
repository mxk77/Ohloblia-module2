package entities;

import island.Location;

public class Deer extends Herbivore {
    public Deer(Location location){
        super(300, 20, 4, 50, location);
    }

    public void reproduce() {
        new Deer(this.getLocation());
    }
}
