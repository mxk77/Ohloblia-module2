public class Predator extends Animal {

    public Predator(double weight, int maxNumberInLocation, int movementSpeed, double foodNeeded) {
        super(weight, maxNumberInLocation, movementSpeed, foodNeeded);
    }

    @Override
    public void move() {
    }

    @Override
    public void multiply() {

    }

    @Override
    public boolean eat() {
        return false;
    }
}