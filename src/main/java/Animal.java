public abstract class Animal {
    // Параметри тварини
    protected double weight;
    protected int maxNumberInLocation;
    protected int movementSpeed;
    protected double foodNeeded;

    protected int satiety;
    protected boolean isAlive = true;

    protected Animal(double weight, int maxNumberInLocation, int movementSpeed, double foodNeeded) {
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

    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }

}
