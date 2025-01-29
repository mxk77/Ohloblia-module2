package com.island.entities;

import com.island.config.AnimalType;
import com.island.config.EatingChances;
import com.island.config.Location;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Predator extends Animal {

    private final double foodNeeded;
    private double satiety;

    protected Predator(double weight, int maxNumberInLocation, int movementSpeed, double foodNeeded, Location location) {
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

    public void decreaseSatiety(){
        if (this.isAlive() && this.satiety>0){
            double reduceSatiety = this.getWeight()*ThreadLocalRandom.current().nextDouble(0,0.3);
            satiety = Math.max(0, this.satiety - reduceSatiety);
        } else {
            this.setDead();
        }
    }

    @Override
    public boolean eat() {
        if (!this.isAlive() || this.getSatiety()>=100){
            return false;
        }

        List<Animal> animalsHere = this.getLocation().getAnimals();

        for (Animal victim : animalsHere){
            int chance = EatingChances.getChance(this.getType(),victim.getType());

            if (victim==this || !victim.isAlive() || chance<=0){
                continue;
            }

            int roll = ThreadLocalRandom.current().nextInt(100);
            if (roll<chance){
                double victimWeight = victim.getWeight();
                this.increaseSatiety(victimWeight);

                victim.setDead();
                this.getLocation().removeAnimal(victim);

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