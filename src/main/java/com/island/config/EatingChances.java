package com.island.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class EatingChances {
    public static final Map<AnimalType, Map<AnimalType, Integer>> CHANCE_MAP = new HashMap<>();

    static {
        // === Вовк (WOLF) ===
        Map<AnimalType, Integer> wolfMap = new HashMap<>();
        wolfMap.put(AnimalType.HORSE, 10);
        wolfMap.put(AnimalType.DEER, 15);
        wolfMap.put(AnimalType.RABBIT, 60);
        wolfMap.put(AnimalType.MOUSE, 80);
        wolfMap.put(AnimalType.GOAT, 60);
        wolfMap.put(AnimalType.SHEEP, 70);
        wolfMap.put(AnimalType.BOAR, 15);
        wolfMap.put(AnimalType.BUFFALO, 10);
        wolfMap.put(AnimalType.DUCK, 40);
        CHANCE_MAP.put(AnimalType.WOLF, wolfMap);

        // === Удав (BOA) ===
        Map<AnimalType, Integer> boaMap = new HashMap<>();
        boaMap.put(AnimalType.FOX, 15);
        boaMap.put(AnimalType.RABBIT, 20);
        boaMap.put(AnimalType.MOUSE, 40);
        boaMap.put(AnimalType.DUCK, 10);
        CHANCE_MAP.put(AnimalType.BOA, boaMap);

        // === Лисиця (FOX) ===
        Map<AnimalType, Integer> foxMap = new HashMap<>();
        foxMap.put(AnimalType.RABBIT, 70);
        foxMap.put(AnimalType.MOUSE, 90);
        foxMap.put(AnimalType.DUCK, 60);
        foxMap.put(AnimalType.CATERPILLAR, 40);
        CHANCE_MAP.put(AnimalType.FOX, foxMap);

        // === Ведмідь (BEAR) ===
        Map<AnimalType, Integer> bearMap = new HashMap<>();
        bearMap.put(AnimalType.BOA, 80);
        bearMap.put(AnimalType.HORSE, 40);
        bearMap.put(AnimalType.DEER, 80);
        bearMap.put(AnimalType.RABBIT, 80);
        bearMap.put(AnimalType.MOUSE, 90);
        bearMap.put(AnimalType.GOAT, 70);
        bearMap.put(AnimalType.SHEEP, 70);
        bearMap.put(AnimalType.BOAR, 50);
        bearMap.put(AnimalType.BUFFALO, 20);
        bearMap.put(AnimalType.DUCK, 10);
        CHANCE_MAP.put(AnimalType.BEAR, bearMap);

        // === Орел (EAGLE) ===
        Map<AnimalType, Integer> eagleMap = new HashMap<>();
        eagleMap.put(AnimalType.FOX, 10);
        eagleMap.put(AnimalType.RABBIT, 90);
        eagleMap.put(AnimalType.MOUSE, 90);
        eagleMap.put(AnimalType.DUCK, 80);
        CHANCE_MAP.put(AnimalType.EAGLE, eagleMap);

        // === Кінь (HORSE) ===
        Map<AnimalType, Integer> horseMap = new HashMap<>();
        horseMap.put(AnimalType.PLANT, 100);
        CHANCE_MAP.put(AnimalType.HORSE, horseMap);

        // === Олень (DEER) ===
        Map<AnimalType, Integer> deerMap = new HashMap<>();
        deerMap.put(AnimalType.PLANT, 100);
        CHANCE_MAP.put(AnimalType.DEER, deerMap);

        // === Кролик (RABBIT) ===
        Map<AnimalType, Integer> rabbitMap = new HashMap<>();
        rabbitMap.put(AnimalType.PLANT, 100);
        CHANCE_MAP.put(AnimalType.RABBIT, rabbitMap);

        // === Миша (MOUSE) ===
        Map<AnimalType, Integer> mouseMap = new HashMap<>();
        mouseMap.put(AnimalType.CATERPILLAR, 90);
        mouseMap.put(AnimalType.PLANT, 100);
        CHANCE_MAP.put(AnimalType.MOUSE, mouseMap);

        // === Коза (GOAT) ===
        Map<AnimalType, Integer> goatMap = new HashMap<>();
        goatMap.put(AnimalType.PLANT, 100);
        CHANCE_MAP.put(AnimalType.GOAT, goatMap);

        // === Вівця (SHEEP) ===
        Map<AnimalType, Integer> sheepMap = new HashMap<>();
        sheepMap.put(AnimalType.PLANT, 100);
        CHANCE_MAP.put(AnimalType.SHEEP, sheepMap);

        // === Кабан (BOAR) ===
        Map<AnimalType, Integer> boarMap = new HashMap<>();
        boarMap.put(AnimalType.MOUSE, 50);
        boarMap.put(AnimalType.CATERPILLAR, 90);
        boarMap.put(AnimalType.PLANT, 100);
        CHANCE_MAP.put(AnimalType.BOAR, boarMap);

        // === Буйвол (BUFFALO) ===
        Map<AnimalType, Integer> buffaloMap = new HashMap<>();
        buffaloMap.put(AnimalType.PLANT, 100);
        CHANCE_MAP.put(AnimalType.BUFFALO, buffaloMap);

        // === Качка (DUCK) ===
        Map<AnimalType, Integer> duckMap = new HashMap<>();
        duckMap.put(AnimalType.CATERPILLAR, 90);
        duckMap.put(AnimalType.PLANT, 100);
        CHANCE_MAP.put(AnimalType.DUCK, duckMap);

        // === Гусінь (CATERPILLAR) ===
        Map<AnimalType, Integer> caterpillarMap = new HashMap<>();
        caterpillarMap.put(AnimalType.PLANT, 100);
        CHANCE_MAP.put(AnimalType.CATERPILLAR, caterpillarMap);
    }

    public static int getChance(AnimalType predator, AnimalType prey) {
        return CHANCE_MAP
                .getOrDefault(predator, Collections.emptyMap())
                .getOrDefault(prey, 0);
    }
}
