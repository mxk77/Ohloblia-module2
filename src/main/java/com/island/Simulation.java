package com.island;

import com.island.config.Island;
import com.island.config.IslandInitializer;
import com.island.config.Location;
import com.island.entities.Animal;
import com.island.entities.Plant;
import com.island.entities.Predator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.function.BiConsumer;

public class Simulation {
    private final Island island;

    private static final int CHUNK_COUNT = 4;

    private final int numberOfTicks;

    private int tickCounter = 0;

    private int movedThisTick = 0;
    private int diedThisTick = 0;
    private int eatenThisTick = 0;
    private int totalEaten = 0;
    private int bornThisTick = 0;
    private int ateThisTick = 0;
    private int plantsGrewThisTick = 0;

    private int totalDied = 0;
    private int totalBorn = 0;
    private int aliveAnimals = 0;
    private boolean allAnimalsDead = true;

    private final Set<Location> locationsWithDead = new HashSet<>();

    private final ScheduledExecutorService executor;
    private final ExecutorService workerPool = Executors.newFixedThreadPool(CHUNK_COUNT);

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
        workerPool.shutdownNow();
    }

    private void runCycle() {
        try {
            tickCounter++;

            movedThisTick = 0;
            diedThisTick = 0;
            bornThisTick = 0;
            ateThisTick = 0;
            aliveAnimals = 0;
            plantsGrewThisTick = 0;

            processInChunks(this::processMoveAndDecreaseSatiety);
            processInChunks(this::processEatAndMultiply);
            processInChunks(this::processAliveAnimals);
            processInChunks(this::processDiedThisTick);
            growPlants();

            totalDied+=diedThisTick;
            totalBorn+=bornThisTick;
            totalEaten+=eatenThisTick;
            printTickStatistics();

            if (numberOfTicks<=tickCounter){
                System.out.println("\nВиконано " + tickCounter + " тактів. Зупиняємо симуляцію.");
                printFinalStatistics();
                stop();
            }

            if (allAnimalsDead) {
                System.out.println("\nУсі тварини померли. Зупиняємо симуляцію.");
                printFinalStatistics();
                stop();
            }

        } catch (Exception e) {
            e.printStackTrace();
            stop();
        }
    }

    private void processInChunks(BiConsumer<Integer, Integer> chunkAction) {
        int width = island.getWIDTH();
        int chunkSize = (int) Math.ceil((double) width / CHUNK_COUNT);

        List<Future<?>> futures = new ArrayList<>(CHUNK_COUNT);

        for (int i = 0; i < CHUNK_COUNT; i++) {
            final int startX = i * chunkSize;
            final int endX = Math.min((i + 1) * chunkSize, width);

            // Передаємо лямбду chunkAction у пул
            Future<?> future = workerPool.submit(() -> chunkAction.accept(startX, endX));
            futures.add(future);
        }

        // Чекаємо на завершення всіх задач
        waitForAllFutures(futures);
    }

    private void processMoveAndDecreaseSatiety(int startX, int endX) {
        for (int x = startX; x < endX; x++) {
            for (int y = 0; y < island.getHEIGHT(); y++) {
                Location location = island.getLocation(x, y);
                List<Animal> animals = new ArrayList<>(location.getAnimals());
                for (Animal animal : animals) {
                    if (animal.move(island)) {
                        synchronized (this) {
                            movedThisTick++;
                        }
                    }
                    animal.decreaseSatiety();
                }
            }
        }
    }

    private void processDiedThisTick(int startX, int endX) {
        for (int x = startX; x < endX; x++) {
            for (int y = 0; y < island.getHEIGHT(); y++) {
                Location location = island.getLocation(x, y);
                List<Animal> animals = new ArrayList<>(location.getAnimals());
                for (Animal animal : animals) {
                    if (!animal.isAlive()){
                        synchronized (this){
                            diedThisTick++;
                            locationsWithDead.add(location);
                        }
                    }
                }
            }
        }
    }

    private void processEatAndMultiply(int startX, int endX) {
        for (int x = startX; x < endX; x++) {
            for (int y = 0; y < island.getHEIGHT(); y++) {
                Location location = island.getLocation(x, y);
                List<Animal> animals = new ArrayList<>(location.getAnimals());
                for (Animal animal : animals) {
                    if (animal.eat()){
                        synchronized (this){
                            ateThisTick++;
                        }
                        if (animal instanceof Predator){
                            synchronized (this){
                                eatenThisTick++;
                            }
                        }
                    }
                    if (animal.multiply()){
                        synchronized (this){
                            bornThisTick++;
                        }
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

    private void processAliveAnimals(int startX, int endX) {
        for (int x = startX; x < endX; x++) {
            for (int y = 0; y < island.getHEIGHT(); y++) {
                Location location = island.getLocation(x, y);
                List<Animal> animals = new ArrayList<>(location.getAnimals());
                for (Animal animal : animals) {
                    if (animal.isAlive()){
                        synchronized (this){
                            allAnimalsDead=false;
                            aliveAnimals++;
                        }
                    }
                }
            }
        }
    }

    private void waitForAllFutures(List<Future<?>> futures) {
        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private void printTickStatistics() {
        System.out.println(
                "\n=== Такт #" + tickCounter + " ===" +
                        "\n  Живих тварин: " + aliveAnimals +
                        "\n  Походило тварин: " + movedThisTick +
                        "\n  Померло тварин з голоду: " + diedThisTick +
                        "\n  З'їдено тварин: " + eatenThisTick +
                        "\n  Народилося тварин: " + bornThisTick +
                        "\n  Поїло тварин: " + ateThisTick +
                        "\n  Виросло рослин: " + plantsGrewThisTick
        );
    }

    private void printFinalStatistics(){
        System.out.println(
                "\n  Живих тварин: " + aliveAnimals +
                        "\n  Померло тварин з голоду за час симуляції: " + totalDied +
                        "\n  З'їдено тварин за час симуляції: " + totalEaten +
                        "\n  Народилося тварин за час симуляції: " + totalBorn
        );
    }
}
