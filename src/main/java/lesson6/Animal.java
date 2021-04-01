package lesson6;

public class Animal {
    private String name;
    private int runRestriction;
    private int swimRestriction;

    public Animal(String name) {
        this.name = name;
    }

    public void setRunRestriction(int runRestriction) {
        this.runRestriction = runRestriction;
    }

    public void setSwimRestriction(int swimRestriction) {
        this.swimRestriction = swimRestriction;
    }

    public void run(int length) {
        if(length>runRestriction){
            System.out.println(name + " не может пробежать " + length + "м. (макс "+ runRestriction+")");
        } else {
            System.out.println(name + " пробежал " + length + " м.");
        }
    }
    public void swim(int length) {
        if(length>swimRestriction){
            System.out.println(name + " не может проплыть " + length + "м. (макс "+ swimRestriction+")");
        } else {
            System.out.println(name + " проплыл " + length + " м.");
        }
    }
}
