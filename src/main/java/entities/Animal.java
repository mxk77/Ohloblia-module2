package entities;

import island.Island;
import island.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.spi.AbstractResourceBundleProvider;

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

    public void move(Island island){
        if (!this.isAlive || this.movementSpeed==0){
            return;
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
                newX=x;
                newY=y;
            }
        }

        this.location.removeAnimal(this);

        Location newLocation = island.getLocation(newX, newY);
        newLocation.addAnimal(this);

        this.location=newLocation;
    }

    public void multiply(){
        long sameSpecies = 0;
        long potentialParents = 0;

        for(Animal animal : this.location.getAnimals()){
            if (animal.getClass().equals(this.getClass())){
                sameSpecies++;
                if (animal.isAlive() && animal.isFull()){
                    potentialParents++;
                }
            }
        }

        if (potentialParents>=2 || sameSpecies<this.maxNumberInLocation){
            this.reproduce();
        }
    }

    public abstract void reproduce();

    public abstract boolean isFull();

    public abstract boolean eat();

    public double getWeight() {
        return weight;
    }

    public int getMaxNumberInLocation() {
        return maxNumberInLocation;
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
}
