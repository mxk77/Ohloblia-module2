package entities;

import island.Location;

public abstract class Predator extends Animal {

    private final double foodNeeded;
    private int satiety;

    protected Predator(double weight, int maxNumberInLocation, int movementSpeed, double foodNeeded, Location location) {
        super(weight, maxNumberInLocation, movementSpeed, location);
        this.foodNeeded=foodNeeded;
    }

    public abstract void reproduce();

    @Override
    public boolean eat() {
        return true;
    }

    public boolean isFull(){
        return satiety>=100;
    }
}