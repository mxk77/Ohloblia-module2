package com.island.entities;

import com.island.config.AnimalType;
import com.island.config.EatingChances;
import com.island.config.Location;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Herbivore extends Animal {

    private final double foodNeeded;
    private double satiety = 50;

    protected Herbivore(double weight, int maxNumberInLocation, int movementSpeed, double foodNeeded, Location location) {
        super(weight, maxNumberInLocation, movementSpeed, location);
        this.foodNeeded=foodNeeded;
    }

    public abstract void reproduce();

    public abstract AnimalType getType();

    protected void increaseSatiety(double eatenWeight){
        double newSatiety = (eatenWeight / this.foodNeeded) * 100;

        this.satiety += newSatiety;
        if (this.satiety>100){
            this.satiety=100;
        }
    }

    @Override
    public boolean eat() {
        if (!this.isAlive() || this.getSatiety()>=100) {
            return false;
        }

        List<Plant> plantsHere = this.getLocation().getPlants();
        for (Plant plant : plantsHere){
            int chance = EatingChances.getChance(this.getType(),plant.getType());

            if (chance<=0){
                continue;
            }

            int roll = ThreadLocalRandom.current().nextInt(100);
            if (roll<chance){
                double plantWeight = plant.getWeight();
                this.increaseSatiety(plantWeight);

                this.getLocation().removePlant(plant);

                return true;
            }
        }
        return false;
    }

    @Override
    public double getSatiety() {
        return satiety;
    }

    @Override
    public void setSatiety(double satiety) {
        this.satiety = satiety;
    }
}
