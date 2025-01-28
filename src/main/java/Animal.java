public abstract class Animal {

    private final double weight;
    private final int maxNumberInLocation;
    private final int movementSpeed;
    private final double foodNeeded;

    private int satiety;
    private boolean isAlive = true;

    public Animal(double weight, int maxNumberInLocation, int movementSpeed, double foodNeeded) {
        this.weight = weight;
        this.maxNumberInLocation = maxNumberInLocation;
        this.movementSpeed = movementSpeed;
        this.foodNeeded = foodNeeded;
    }

    public abstract void move();

    public abstract void multiply();

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

    public void setAlive(boolean alive){
        this.isAlive=alive;
    }
}
