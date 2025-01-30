package com.island.config;

import com.island.entities.*;

import java.lang.reflect.Constructor;
import java.util.concurrent.ThreadLocalRandom;

public class IslandInitializer {

    private final Island island;
    private static final double FILL_FACTOR = 0.001;

    public IslandInitializer(Island island){
        this.island = island;
    }

    public void initializeAll(){
        spawnAnimals(Bear.class);
        spawnAnimals(Boa.class);
        spawnAnimals(Boar.class);
        spawnAnimals(Buffalo.class);
        spawnAnimals(Caterpillar.class);
        spawnAnimals(Deer.class);
        spawnAnimals(Duck.class);
        spawnAnimals(Eagle.class);
        spawnAnimals(Fox.class);
        spawnAnimals(Goat.class);
        spawnAnimals(Horse.class);
        spawnAnimals(Mouse.class);
        spawnAnimals(Rabbit.class);
        spawnAnimals(Sheep.class);
        spawnAnimals(Wolf.class);

        spawnPlants(Plant.class);
    }

    private  <T extends Animal> void spawnAnimals(Class<T> animalClass) {
        try {
            // 1. Дістанемо конструктор (Location)
            Constructor<T> constructor = animalClass.getConstructor(Location.class);

            // 2. Створимо "тимчасову" тварину
            Location dummyLocation = island.getLocation(0, 0);
            T dummy = constructor.newInstance(dummyLocation);

            // 3. Через гетер дізнаємося максимальну кількість на локації
            int maxNumberInLocation = dummy.getMaxNumberInLocation();

            // 4. Обчислюємо, скільки екземплярів (всього на острів) створити
            int totalCells = island.getWIDTH() * island.getHEIGHT();
            int maxPossible = (int) (maxNumberInLocation * FILL_FACTOR * totalCells);

            int totalCount = ThreadLocalRandom.current().nextInt(maxPossible + 1);

            int created = 0;
            while (created < totalCount) {
                int x = ThreadLocalRandom.current().nextInt(island.getWIDTH());
                int y = ThreadLocalRandom.current().nextInt(island.getHEIGHT());
                Location location = island.getLocation(x, y);

                long sameSpeciesCount = location.getAnimals().stream()
                        .filter(a -> a.getClass().equals(animalClass))
                        .count();

                if (sameSpeciesCount < maxNumberInLocation) {
                    T animal = constructor.newInstance(location);
                    location.addAnimal(animal);
                    created++;
                }
            }

            System.out.println("Створено " + animalClass.getSimpleName() + ": " + created);

        } catch (NoSuchMethodException e) {
            System.err.println("У класі " + animalClass.getSimpleName()
                    + " немає конструктора (Location): " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void spawnPlants(Class<Plant> plantClass) {
        try {
            // 1. Дістанемо конструктор (Location)
            Constructor<Plant> constructor = plantClass.getConstructor(Location.class);

            // 2. Створимо "тимчасову" рослину
            Location dummyLocation = island.getLocation(0, 0);
            Plant dummy = constructor.newInstance(dummyLocation);

            // 3. Через гетер дізнаємося максимальну кількість на локації
            int maxNumberInLocation = dummy.getMaxNumberInLocation();

            // 4. Обчислюємо, скільки екземплярів (всього на острів) створити
            int totalCells = island.getWIDTH() * island.getHEIGHT();
            int maxPossible = (int) (maxNumberInLocation * FILL_FACTOR * totalCells);

            int totalCount = ThreadLocalRandom.current().nextInt(maxPossible + 1);

            int created = 0;
            while (created < totalCount) {
                int x = ThreadLocalRandom.current().nextInt(island.getWIDTH());
                int y = ThreadLocalRandom.current().nextInt(island.getHEIGHT());
                Location location = island.getLocation(x, y);

                long sameSpeciesCount = location.getPlants().stream()
                        .filter(p -> p.getClass().equals(plantClass))
                        .count();

                if (sameSpeciesCount < maxNumberInLocation) {
                    Plant plant = constructor.newInstance(location);
                    location.addPlant(plant);
                    created++;
                }
            }

            System.out.println("Створено " + plantClass.getSimpleName() + ": " + created);

        } catch (NoSuchMethodException e) {
            System.err.println("У класі " + plantClass.getSimpleName()
                    + " немає конструктора (Location): " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
