package com.island.entities;

import com.island.config.AnimalType;
import com.island.config.EatingChances;
import com.island.config.Location;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Boar extends Herbivore {
    public Boar(Location location){
        super(400, 50, 2, 50, location);
    }

    private boolean tryEatOther(){
        if (!this.isAlive() || this.isFull()){
            return false;
        }

        List<Animal> animalsHere = this.getLocation().getAnimals();

        for (Animal victim : animalsHere){
            int chance = EatingChances.getChance(this.getType(),victim.getType());

            if (victim==this || !victim.isAlive() || chance<=0){
                continue;
            }

            int roll = ThreadLocalRandom.current().nextInt(100);
            if (roll<chance){
                double victimWeight = victim.getWeight();
                this.increaseSatiety(victimWeight);

                victim.setDead();
                this.getLocation().removeAnimal(victim);

                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eat() {
        boolean ateOther = tryEatOther();
        if (ateOther){
            return true;
        } else {
            return super.eat();
        }
    }

    @Override
    public void reproduce() {
        new Boar(this.getLocation());
    }

    @Override
    public AnimalType getType() {
        return AnimalType.BOAR;
    }
}
