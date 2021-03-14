package lesson1;

public class lesson1Tasks {

    // Задание 3
    // метод, вычисляющий выражение a * (b + (c / d)) и возвращающий результат,
    // где a, b, c, d – аргументы этого метода, имеющие тип float
    static float computeExpression(float a, float b, float c, float d) {
        if(d==0) {
            return 0; // при попытке деления на 0
        } else {
            return a * (b + (c / d));
        }
    }

    // Задание 4
    // метод, принимающий на вход два целых числа и проверяющий, что их сумма лежит в пределах от 10 до 20 (включительно),
    // если да – вернуть true, в противном случае – false
    static boolean checkSumInterval(int a, int b) {
        int sumVar = a + b;
        return (sumVar >= 10) && (sumVar <= 20);
    }

    // Задание 5
    // метод, которому в качестве параметра передается целое число,
    // метод должен напечатать в консоль, положительное ли число передали или отрицательное (ноль считаем положительным числом)
    static void printPosOrNeg(int a) {
        System.out.println(a >= 0 ? "Положительное" : "Отрицательное");
    }

    // Задание 6
    // метод, которому в качестве параметра передается целое число,
    // метод должен напечатать в консоль, положительное ли число передали или отрицательное (ноль считаем положительным числом)
    static boolean isNegative(int a) {
        return a < 0;
    }

    // Задание 7
    // метод, которому в качестве параметра передается строка, обозначающая имя.
    // Метод должен вывести в консоль сообщение «Привет, указанное_имя!».
    static void helloSubj(String nameVar) {
        System.out.println("Привет, " + nameVar + "!");
    }

    // Задание 8
    // метод, который определяет, является ли год високосным, и выводит сообщение в консоль.
    // Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный
    static void printIsLeapYear(short yearVar) {
        String resVar;

        if (yearVar <= 0) {
            resVar = "Некорректный год";
        } else {
            if (yearVar % 4 == 0) {
                if (yearVar % 100 == 0) {
                    if (yearVar % 400 == 0) {
                        resVar = "Високосный год";
                    } else {
                        resVar = "Не високосный год";
                    }
                } else {
                    resVar = "Високосный год";
                }
            } else {
                resVar = "Не високосный год";
            }
        }
        System.out.println(resVar);
    }

    // объявлен метод main в новом проекте (задание 1)
    public static void main(String[] args) {
        // инициализированы переменные всех типов (задание 2)
        byte byteVarPositive = 127, byteVarNegative = -128;
        short shortVarPositive = 32000, shortVarNegative = -32000;
        int intVarPositive = 1_000_000_000, intVarNagative = -1_000_000_000;
        long longVarPositive = 10_000_000_000_000_000L, longVarNegative = -10_000_000_000_000_000L;
        float floatVarPositive = 10.3f, floatVarNegative = -10.3f;
        double doubleVarPositive = 11.5, doubleVarNegative = -11.5;
        boolean boolVar = true;
        char charVar = 'A';
        String stringVar = "Test String";

        // вызов computeExpression (задание 3)
        float a3 = 2.2f, b3 = 2.0f, c3 = 4.4f, d3 = 4.4f;
        float expressionResult = computeExpression(a3, b3, c3, d3);
        System.out.println("\nTask3 computeExpression result = " + expressionResult);

        // вызов checkSumInterval (задание 4)
        int a4 = 19, b4 = 2;
        boolean intervalResult = checkSumInterval(a4, b4);
        System.out.println("\nTask4 checkSumInterval result = " + intervalResult);

        // вызов printPosOrNeg (задание 5)
        int a5 = -10;
        System.out.println("\nTask5 printPosOrNeg result:");
        printPosOrNeg(a5);

        //вызов printPosOrNeg (задание 6)
        int a6 = -10;
        boolean signResult = isNegative(a6);
        System.out.println("\nTask6 isNegative result = " + signResult);

        //вызов helloSubj (задание 7)
        String subj = "Alex";
        System.out.println("\nTask7 helloSubj result:");
        helloSubj(subj);

        //вызов printIsLeapYear (задание 8)
        short leapYear = 2020;
        System.out.println("\nTask8 printIsLeapYear result:");
        printIsLeapYear(leapYear);
    }
}
