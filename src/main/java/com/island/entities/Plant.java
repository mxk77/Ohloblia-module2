package com.island.entities;

import com.island.config.AnimalType;
import com.island.config.Location;

import java.util.ArrayList;
import java.util.List;

public class Plant {
    private static final double WEIGHT=1;
    private static final int MAX_NUMBER_IN_LOCATION=200;

    Location location;

    public  Plant(Location location){
        this.location=location;
        location.addPlant(this);
    }

    public static int grow(Location location){
        int plantsInLocation = 0;
        double deadAnimalsWeight = 0;
        List<Animal> deadAnimals = new ArrayList<>();

        for (Animal animal : location.getAnimals()){
            if (!animal.isAlive()) {
                deadAnimalsWeight += animal.getWeight();
                deadAnimals.add(animal);
            }
        }

        for (Plant _ : location.getPlants()){
            plantsInLocation++;
        }

        location.getAnimals().removeAll(deadAnimals);

        int plantsFromDead = (int) Math.ceil(deadAnimalsWeight);
        int canAdd = MAX_NUMBER_IN_LOCATION - plantsInLocation;

        if (canAdd <= 0){
            return 0;
        }

        int toAdd = Math.min(plantsFromDead, canAdd);

        for (int i = 0; i < toAdd; i++){
            new Plant(location);
        }

        return toAdd;
    }

    public double getWeight() {
        return WEIGHT;
    }

    public int getMaxNumberInLocation() {
        return MAX_NUMBER_IN_LOCATION;
    }

    public AnimalType getType(){
        return AnimalType.PLANT;
    }
}
