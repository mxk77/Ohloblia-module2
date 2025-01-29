package com.island.config;

import com.island.entities.Animal;
import com.island.entities.Plant;

import java.lang.reflect.Constructor;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

public class IslandInitializer {

    private final Island island;
    private final double fillFactor = 0.8;

    public IslandInitializer(Island island){
        this.island = island;
    }

    public <T extends Animal> void spawnAnimals(Class<T> animalClass) {
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
            int maxPossible = (int) (maxNumberInLocation * fillFactor * totalCells);

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

    public <T extends Plant> void spawnPlants(Class<T> plantClass) {
        try {
            // 1. Дістанемо конструктор (Location)
            Constructor<T> constructor = plantClass.getConstructor(Location.class);

            // 2. Створимо "тимчасову" рослину
            Location dummyLocation = island.getLocation(0, 0);
            T dummy = constructor.newInstance(dummyLocation);

            // 3. Через гетер дізнаємося максимальну кількість на локації
            int maxNumberInLocation = dummy.getMaxNumberInLocation();

            // 4. Обчислюємо, скільки екземплярів (всього на острів) створити
            int totalCells = island.getWIDTH() * island.getHEIGHT();
            int maxPossible = (int) (maxNumberInLocation * fillFactor * totalCells);

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
                    T plant = constructor.newInstance(location);
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
