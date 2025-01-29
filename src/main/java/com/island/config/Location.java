package com.island.config;

import com.island.entities.Animal;
import com.island.entities.Plant;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private final int x;
    private final int y;

    private final List<Animal> animals = new ArrayList<>();
    private final List<Plant> plants = new ArrayList<>();

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public void removePlant(Plant plant) {
        plants.remove(plant);
    }
}
