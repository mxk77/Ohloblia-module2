package com.island.entities;

import com.island.config.AnimalType;
import com.island.config.Island;
import com.island.config.Location;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal {

    private final double weight;
    private final int maxNumberInLocation;
    private final int movementSpeed;

    private boolean isAlive = true;
    private Location location;

    protected Animal(double weight, int maxNumberInLocation, int movementSpeed, Location location) {
        this.weight = weight;
        this.maxNumberInLocation = maxNumberInLocation;
        this.movementSpeed = movementSpeed;
        this.location=location;
        location.addAnimal(this);
    }

    public boolean move(Island island){
        if (!this.isAlive || this.movementSpeed==0){
            return false;
        }

        int direction = ThreadLocalRandom.current().nextInt(4);

        int steps = ThreadLocalRandom.current().nextInt(1, this.movementSpeed+1);

        int x = this.location.getX();
        int y = this.location.getY();

        int newX = x;
        int newY = y;

        switch (direction){
            case 0 -> newY=Math.max(y-steps, 0); //UP
            case 1 -> newY=Math.min(y+steps, island.getHEIGHT() - 1); //DOWN
            case 2 -> newX=Math.max(x-steps, 0); //LEFT
            case 3 -> newX=Math.min(x+steps, island.getWIDTH() - 1); //RIGHT
            default -> {
                return false;
            }
        }

        if (newX == x && newY == y) {
            return false;
        }

        this.location.removeAnimal(this);

        Location newLocation = island.getLocation(newX, newY);
        newLocation.addAnimal(this);

        this.location=newLocation;

        return true;
    }

    public boolean multiply(){
        long sameSpecies = 0;
        long potentialParents = 0;

        for(Animal animal : this.getLocation().getAnimals()){
            if (animal.getClass().equals(this.getClass())){
                sameSpecies++;
                if (animal.isAlive() && animal.getSatiety()>=100){
                    potentialParents++;
                }
            }
        }

        if (potentialParents>=2 || sameSpecies<this.getMaxNumberInLocation()){
            this.reproduce();
            return true;
        }

        return false;
    }

    public void decreaseSatiety(){
        if (this.isAlive() && this.getSatiety()>0){
            double reduceSatiety = this.getWeight()* ThreadLocalRandom.current().nextDouble(0,0.2);
            setSatiety(Math.max(0, this.getSatiety() - reduceSatiety));
        } else {
            this.setDead();
        }
    }

    public abstract void reproduce();


    public abstract double getSatiety();

    public abstract void setSatiety(double satiety);

    public abstract boolean eat();

    public abstract AnimalType getType();

    public double getWeight() {
        return weight;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setDead(){
        this.isAlive=false;
    }

    public Location getLocation() {
        return location;
    }

    public int getMaxNumberInLocation() {
        return maxNumberInLocation;
    }
}
