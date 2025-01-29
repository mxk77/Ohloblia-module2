package com.island;

import com.island.config.Island;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Simulation {
    private final Island island;

    private int tickCounter = 0;

    private int diedThisTick = 0;
    private int bornThisTick = 0;
    private int ateThisTick = 0;
    private int multipliedThisTick = 0;

    private final ScheduledExecutorService executor;

    public Simulation(Island island) {
        this.island = island;
        this.executor = Executors.newScheduledThreadPool(1);
    }

    public void start() {
        printInitialStatistics();

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

            diedThisTick = 0;
            bornThisTick = 0;
            ateThisTick = 0;
            multipliedThisTick = 0;

            moveAndDecreaseSatiety();

            checkAndHandleDeaths();

            if (tickCounter % 2 == 0) {
                eatAll();
                multiplyAll();
            }

            if (tickCounter % 3 == 0) {
                convertDeadAnimalsToPlants();
            }

            printTickStatistics();

            if (isAllAnimalsDead()) {
                System.out.println("Усі тварини померли. Зупиняємо симуляцію.");
                stop();
            }

        } catch (Exception e) {
            e.printStackTrace();
            stop();
        }
    }


    private void printInitialStatistics() {

    }

    private void moveAndDecreaseSatiety() {

    }

    private void checkAndHandleDeaths() {

    }

    private void eatAll() {

    }

    private void multiplyAll() {

    }

    private void convertDeadAnimalsToPlants() {

    }

    private boolean isAllAnimalsDead() {
        return true;
    }

    private void printTickStatistics() {
        System.out.println("=== Такт #" + tickCounter + " ===");
        System.out.println("  Померло тварин: " + diedThisTick);
        System.out.println("  Народилося тварин: " + bornThisTick);
        System.out.println("  Поїли тварин: " + ateThisTick);
        System.out.println("  Розмножилися: " + multipliedThisTick);
        // Ще можна вивести поточну кількість усіх живих тварин чи рослин
        // (потрібно підрахувати)
    }
}
