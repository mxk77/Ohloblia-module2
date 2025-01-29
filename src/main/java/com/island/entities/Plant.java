package com.island.entities;

import com.island.config.AnimalType;
import com.island.config.Location;

public class Plant {
    private static double weight=1;
    private static final int maxNumberInLocation=200;

    Location location;

    public  Plant(Location location){
        this.location=location;
    }

    public double getWeight() {
        return weight;
    }

    public int getMaxNumberInLocation() {
        return maxNumberInLocation;
    }

    public AnimalType getType(){
        return AnimalType.PLANT;
    }
}
