package lesson7;

public class CatPlateTest {
    public static void main(String[] args) {
        Cat[] cats = new Cat[10];
        Plate plate = new Plate(20);
        plate.plateinfo();

        for (int i = 0; i < cats.length; i++) {
            cats[i] = new Cat("Cat #"+i, (int)(Math.random()*10+1));
            //System.out.println(cats[i].getName() + " appetite is: "+ cats[i].getAppetite());
            cats[i].eat(plate);
            System.out.println(cats[i].getName() + " is " + (cats[i].getSatiety()?"not ":"") + "hungry");
            //plate.plateinfo();
        }
        plate.increaseFood(25);
        plate.plateinfo();
    }
}
