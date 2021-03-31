package lesson6;

public class Dog extends Animal {
    private int runRestriction = 500;
    private int swimRestriction = 10;

    public Dog(String name) {
        super(name);
        super.setRunRestriction(this.runRestriction);
        super.setSwimRestriction(this.swimRestriction);
    }

}
