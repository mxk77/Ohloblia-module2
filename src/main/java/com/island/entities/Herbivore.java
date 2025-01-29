package com.island.entities;

import com.island.config.Location;

public abstract class Herbivore extends Animal {

    private final double foodNeeded;
    private int satiety;

    protected Herbivore(double weight, int maxNumberInLocation, int movementSpeed, double foodNeeded, Location location) {
        super(weight, maxNumberInLocation, movementSpeed, location);
        this.foodNeeded=foodNeeded;
    }

    public abstract void reproduce();

    @Override
    public boolean eat() {
        return false;
    }

    @Override
    public boolean isFull(){
        return satiety>=100;
    }
}
