package lesson3;

import java.util.Scanner;

public class Lesson3Tasks {

    // Программа, которая загадывает случайное число от 0 до 9 и пользователю дается 3 попытки угадать это число.
    // При каждой попытке компьютер должен сообщить, больше ли указанное пользователем число, чем загаданное, или меньше.
    // После победы или проигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).

    static void guessNumbersGame() {
        // 3 попытки
        int numberOfTries = 3;
        // Случайное число от 0 до 9
        int randomNumber =  (int) Math.round(Math.random() * 10);
        //System.out.println("Загадано число от 0 до 9. Угадайте число " + randomNumber); // Отладка
        System.out.println("Загадано число от 0 до 9. Угадайте число, кол-во попыток: " + numberOfTries);

        for (int i = 1; i <= numberOfTries; i++) {
            System.out.println();
            System.out.println("Попытка " + i);
            System.out.print("Введите число: ");
            Scanner in = new Scanner(System.in);
            int guessVariant = in.nextInt();

            if (randomNumber > guessVariant) {
                System.out.println();
                System.out.println("Загаданное число больше");
            } else if (randomNumber < guessVariant) {
                System.out.println();
                System.out.println("Загаданое число меньше");
            } else {
                System.out.println();
                System.out.println("Угадали!");
                return;
            }
        }
        System.out.println();
        System.out.println("Не угадали!");
    }


    //При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя, сравнивает его с загаданным словом
    // и сообщает, правильно ли ответил пользователь. Если слово не угадано, компьютер показывает буквы, которые стоят на своих местах.
    //apple – загаданное
    //apricot - ответ игрока
    //ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
    //Для сравнения двух слов посимвольно можно пользоваться:
    //String str = "apple";
    //char a = str.charAt(0); - метод вернет char, который стоит в слове str на первой позиции
    //Играем до тех пор, пока игрок не отгадает слово.
    //Используем только маленькие буквы.

    static void guessWordsGame() {
        Scanner in = new Scanner(System.in);
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
                "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut",
                "pear", "pepper", "pineapple", "pumpkin", "potato"};
        // Случайное слово из массива в 25 элементов
        int randomNumber =  (int) Math.round(Math.random() * words.length);
        boolean guessed = false;

        //System.out.print("Загадано слово, угадайте растение " + words[randomNumber]); // отладка
        System.out.print("Загадано слово, угадайте растение");
        do {
            System.out.println();
            System.out.print("Введите ваш вариант: ");
            String guessVariant = in.nextLine().toLowerCase();  //Только маленькие буквы
            System.out.println(guessVariant);
            if (guessVariant.equals(words[randomNumber])) { // Угадали
                guessed = true;
                break;
            }
            // Отображаем совпадающие буквы
            char[] wordArray = words[randomNumber].toCharArray();
            char[] guessArray = guessVariant.toCharArray();
            int cnt = 0;

            for (int i = 0; i < Math.min(wordArray.length,guessArray.length); i++) {
                System.out.print(wordArray[i] == guessArray[i] ? guessArray[i] : "#");
                cnt++;
            }

            // Добиваем до 15 символов
            for (int i = 0; i < (15-cnt); i++) {
                System.out.print("#");
            }

        } while (!guessed);  //Пока не угадаем

        System.out.println();
        System.out.println("Угадали! \nПока!");
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        // Задание 1 - угадывание числа от 0 до 9

        boolean repeatNumbersGame = true;
        do {
            guessNumbersGame();
            System.out.println();
            System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
            repeatNumbersGame = in.nextInt() == 1;
        } while (repeatNumbersGame);
        System.out.println();
        System.out.println("Пока!");
        System.out.println();


        // Задание 2 - угадывание слова
        guessWordsGame();

    }
}
