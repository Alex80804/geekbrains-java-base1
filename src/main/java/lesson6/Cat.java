package lesson6;

public class Cat extends Animal {

    private int runRestriction = 200;
    private int swimRestriction = 0;

    public Cat(String name) {
        super(name);
        super.setRunRestriction(this.runRestriction);
        super.setSwimRestriction(this.swimRestriction);
    }


}
