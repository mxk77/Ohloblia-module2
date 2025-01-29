package com.island;

import com.island.config.Island;
import com.island.config.IslandInitializer;
import com.island.config.Location;
import com.island.entities.Animal;
import com.island.entities.Plant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Simulation {
    private final Island island;

    private final int numberOfTicks;

    private int tickCounter = 0;

    private int movedThisTick = 0;
    private int diedThisTick = 0;
    private int bornThisTick = 0;
    private int ateThisTick = 0;
    private int plantsGrewThisTick = 0;

    private final Set<Location> locationsWithDead = new HashSet<>();

    private final ScheduledExecutorService executor;

    public Simulation(Island island, int numberOfTicks) {
        this.island = island;
        this.numberOfTicks = numberOfTicks;
        this.executor = Executors.newScheduledThreadPool(1);
    }

    public void start(IslandInitializer initializer) {
        initializer.initializeAll();
        executor.scheduleAtFixedRate(
                this::runCycle,
                0,
                1,
                TimeUnit.SECONDS
        );
    }

    public void stop() {
        executor.shutdownNow();
    }

    private void runCycle() {
        try {
            tickCounter++;

            movedThisTick = 0;
            diedThisTick = 0;
            bornThisTick = 0;
            ateThisTick = 0;
            plantsGrewThisTick = 0;

            moveAndDecreaseSatiety();
            diedThisTick();


            if (tickCounter % 2 == 0) {
                eatAndMultiply();
            }

            if (tickCounter % 3 == 0) {
                growPlants();
            }

            printTickStatistics();

            if (numberOfTicks<=tickCounter){
                System.out.println("Виконано " + tickCounter + " тактів. Зупиняємо симуляцію.");
                stop();
            }

            if (isAllAnimalsDead()) {
                System.out.println("Усі тварини померли. Зупиняємо симуляцію.");
                stop();
            }

        } catch (Exception e) {
            e.printStackTrace();
            stop();
        }
    }

    private void moveAndDecreaseSatiety() {
        for (int x = 0; x < island.getWIDTH(); x++) {
            for (int y = 0; y< island.getHEIGHT(); y++){
                Location location=island.getLocation(x,y);
                List<Animal> animals = new ArrayList<>(location.getAnimals());
                for (Animal animal : animals){
                    if (animal.move(island)){
                        movedThisTick++;
                    }
                    animal.decreaseSatiety();
                }
            }

        }
    }

    private void diedThisTick(){

        for (int x = 0; x < island.getWIDTH(); x++) {
            for (int y = 0; y< island.getHEIGHT(); y++){
                Location location=island.getLocation(x,y);
                List<Animal> animals = new ArrayList<>(location.getAnimals());
                for (Animal animal : animals){
                    if (!animal.isAlive()){
                        diedThisTick++;
                        locationsWithDead.add(location);
                    }
                }
            }

        }
    }


    private void eatAndMultiply() {
        for (int x = 0; x < island.getWIDTH(); x++) {
            for (int y = 0; y< island.getHEIGHT(); y++){
                Location location=island.getLocation(x,y);
                List<Animal> animals = new ArrayList<>(location.getAnimals());
                for (Animal animal : animals){
                    if (animal.eat()){
                        ateThisTick++;
                    }
                    if (animal.multiply()){
                        bornThisTick++;
                    }
                }
            }

        }
    }

    private void growPlants(){
        for (Location location : locationsWithDead){
            plantsGrewThisTick += Plant.grow(location);
        }
        locationsWithDead.clear();
    }

    private boolean isAllAnimalsDead() {
        for (int x = 0; x < island.getWIDTH(); x++) {
            for (int y = 0; y< island.getHEIGHT(); y++){
                Location location=island.getLocation(x,y);
                List<Animal> animals = new ArrayList<>(location.getAnimals());
                for (Animal animal : animals){
                    if (animal.isAlive()){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void printTickStatistics() {
        System.out.println("=== Такт #" + tickCounter + " ===");
        System.out.println("  Походило тварин: " + movedThisTick);
        System.out.println("  Померло тварин: " + diedThisTick);
        System.out.println("  Народилося тварин: " + bornThisTick);
        System.out.println("  Поїло тварин: " + ateThisTick);
        System.out.println("  Виросло рослин: " + plantsGrewThisTick);

    }
}
