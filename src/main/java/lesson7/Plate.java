package lesson7;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public void plateinfo() {
        System.out.println("Еды в тарелке: " + getFood());
    }

    public int getFood() {
        return food;
    }

    public boolean decreaseFood(int delta) {
        if(this.food>=delta){
            this.food-= delta;
            return true;
        } else {
            return false;
        }
    }

    public void increaseFood(int delta) {
        this.food+=delta;
    }
}
