package com.island;

import com.island.config.Island;
import com.island.config.IslandInitializer;

public class Main {
    public static void main(String[] args) {
        Island island = new Island();
        IslandInitializer initializer = new IslandInitializer(island);
        Simulation simulation = new Simulation(island, 100);

        simulation.start(initializer);
    }
}