package com.island.entities;

import com.island.config.AnimalType;
import com.island.config.Location;

public abstract class Herbivore extends Animal {

    private final double foodNeeded;
    private double satiety;

    protected Herbivore(double weight, int maxNumberInLocation, int movementSpeed, double foodNeeded, Location location) {
        super(weight, maxNumberInLocation, movementSpeed, location);
        this.foodNeeded=foodNeeded;
    }

    public abstract void reproduce();

    public abstract AnimalType getType();


    @Override
    public boolean eat() {
        return false;
    }

    @Override
    public boolean isFull(){
        return satiety>=100;
    }
}
