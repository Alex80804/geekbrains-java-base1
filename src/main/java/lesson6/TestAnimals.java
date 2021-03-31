package lesson6;

public class TestAnimals {
    public static void main(String[] args) {
        Dog Rex = new Dog("Рекс");
        Cat Tom = new Cat("Том");
        Rex.run(550);
        Rex.swim(5);
        Tom.run(50);
        Tom.swim(10);
    }
}
