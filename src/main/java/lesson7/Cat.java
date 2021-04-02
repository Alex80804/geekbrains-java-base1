package lesson7;

public class Cat {
    private String name;
    private int appetite;
    private boolean satiety;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.satiety = false;
    }

    public String getName() {
        return name;
    }

    public int getAppetite() {
        return appetite;
    }

    public boolean getSatiety() {
        return satiety;
    }

    public void eat(Plate plate) {
        if(!plate.decreaseFood(this.appetite)){
            System.out.println("Не хватает еды в тарелке");
        } else {
            this.satiety = true;
        }
    }
}
