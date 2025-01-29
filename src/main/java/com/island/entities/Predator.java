package com.island.entities;

import com.island.config.Location;

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

    @Override
    public boolean isFull(){
        return satiety>=100;
    }
}