public class Plant {
    private static double weight=1;
    private static final int maxNumberInLocation=200;

    private boolean isAlive;

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
        isAlive = alive;
    }
}
