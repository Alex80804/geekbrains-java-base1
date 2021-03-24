package lesson4;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Lesson4Tasks {

    //Символы
    public static char X = 'X';
    public static char O = 'O';
    public static char EMPTY = '.';

    public static int DIM = 5;      // Размерность поля
    public static int WINCOUNT = 4; // Символов подряд для выигрыша

    public static char[][] pitch = new char[DIM][DIM];
    public static int MAXTURNS = DIM*DIM; // Максимум ходов

    /* Отладка
    public static char[][] pitch = {{'X', 'X', 'O', 'O', 'X'},
                                    {'O', 'X', 'O', 'O', 'X'},
                                    {'X', 'O', 'O', 'Х', 'X'},
                                    {'O', 'O', 'X', 'X', 'O'},
                                    {'O', 'O', 'X', 'O', 'O'}};
    public static char[][] pitch = {{'X', 'X', 'O', 'O', 'X'},
                                    {'O', 'X', 'O', 'O', 'X'},
                                    {'X', 'O', 'X', 'Х', 'X'},
                                    {'O', 'O', 'X', 'X', 'O'},
                                    {'O', 'O', 'X', 'O', 'O'}};
    */

    // Инициализация поля
    public static void initPitch() {
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                pitch[i][j] = EMPTY;
            }
        }
    }

    // Печать поля
    public static void printPitch() {
        for (int i = 0; i < DIM; i++) {
            System.out.println();
            for (int j = 0; j < DIM; j++) {
                System.out.print(pitch[i][j] + " ");
            }
        }
        System.out.println();
    }

    // Поиск последовательности символов из заданного кол-ва в рядах
    public static boolean isCharCountFound(char side, int numcount) {

        int maxMatch; // Максимальное кол-во совпавших символов подряд
        boolean found = false;

        //Ищем numcount заданных символов по горизонтали
        for (int i = 0; i < DIM; i++) {
            maxMatch = 0;
            for (int j = 0; j < DIM; j++) {
                if (pitch[i][j]==side) {
                    maxMatch++;
                    if(maxMatch>=numcount) {
                        found = true;
                    }
                } else {
                    maxMatch = 0;
                }
            }
        }
        //Ищем numcount заданных символов по вертикали
        for (int j = 0; j < DIM; j++) {
            maxMatch = 0;
            for (int i = 0; i < DIM; i++) {
                if (pitch[i][j]==side) {
                    maxMatch++;
                    if(maxMatch>=numcount) {
                        found = true;
                    }
                } else {
                    maxMatch = 0;
                }
            }
        }

        //Ищем numcount заданных символов по диагоналям
        maxMatch = 0;
        for (int i = 0; i < DIM; i++) {
            if (pitch[i][i]==side) {
                maxMatch++;
                if(maxMatch>=numcount) {
                    found = true;
                }
            } else {
                maxMatch = 0;
            }
        }
        maxMatch = 0;
        for (int i = 0; i < DIM; i++) {
            if (pitch[i][DIM-i-1]==side) {
                maxMatch++;
                if(maxMatch>=numcount) {
                    found = true;
                }
            } else {
                maxMatch = 0;
            }
        }
        return found;
    }

    // Поиск не закрытой с 2-х сторон последовательности символов из заданного кол-ва в рядах для ИИ
    public static int[][] findOpenCharCount(char side, int numcount) {

        int maxMatch; // Максимальное вол-во символов подряд
        int[][] openCoords = new int[1][2];
        openCoords[0][0]=-1; openCoords[0][1]=-1;

        //Ищем numcount заданных символов по горизонтали
        for (int i = 0; i < DIM; i++) {
            maxMatch = 0;
            for (int j = 0; j < DIM; j++) {
                if (pitch[i][j]==side) {
                    maxMatch++;
                    if(maxMatch>=numcount) { // и если нашли, то проверяем есть ли пустые по бокам
                        if(j-maxMatch>=0&&pitch[i][j-maxMatch]==EMPTY) {
                            openCoords[0][0]=i; openCoords[0][1]=j-maxMatch;
                        }
                        if(j+1<=DIM-1&&pitch[i][j+1]==EMPTY) {
                            openCoords[0][0]=i; openCoords[0][1]=j+1;
                        }
                    }
                } else {
                    maxMatch = 0;
                }
            }
        }
        //Ищем numcount заданных символов по вертикали
        for (int j = 0; j < DIM; j++) {
            maxMatch = 0;
            for (int i = 0; i < DIM; i++) {
                if (pitch[i][j]==side) {
                    maxMatch++;
                    if(maxMatch>=numcount) { // и если нашли, то проверяем есть ли пустые по бокам
                        if(i-maxMatch>=0&&pitch[i-maxMatch][j]==EMPTY) {
                            openCoords[0][0]=i-maxMatch; openCoords[0][1]=j;
                        }
                        if(i+1<=DIM-1&&pitch[i+1][j]==EMPTY) {
                            openCoords[0][0]=i+1; openCoords[0][1]=j;
                        }
                    }
                } else {
                    maxMatch = 0;
                }
            }
        }

        //Ищем numcount заданных символов по диагоналям
        maxMatch = 0;
        for (int i = 0; i < DIM; i++) {
            if (pitch[i][i]==side) {
                maxMatch++;
                if(maxMatch>=numcount) { // и если нашли, то проверяем есть ли пустые по бокам
                    if(i-maxMatch>=0&&pitch[i-maxMatch][i-maxMatch]==EMPTY) {
                        openCoords[0][0]=i-maxMatch; openCoords[0][1]=i-maxMatch;
                    }
                    if(i+1<=DIM-1&&pitch[i+1][i+1]==EMPTY) {
                        openCoords[0][0]=i+1; openCoords[0][1]=i+1;
                    }
                }
            } else {
                maxMatch = 0;
            }
        }

        maxMatch = 0;
        for (int i = 0; i < DIM; i++) {
            if (pitch[i][DIM-i-1]==side) {
                maxMatch++;
                if(maxMatch>=numcount) { // и если нашли, то проверяем есть ли пустые по бокам
                    if(i-maxMatch>=0&&pitch[i-maxMatch][DIM-i-1+maxMatch]==EMPTY) {
                        openCoords[0][0]=i-maxMatch; openCoords[0][1]=DIM-i-1+maxMatch;
                    }
                    if(i+1<=DIM-1&&pitch[i+1][DIM-i-1-1]==EMPTY) {
                        openCoords[0][0]=i+1; openCoords[0][1]=DIM-i-1-1;
                    }
                }
            } else {
                maxMatch = 0;
            }
        }
        return openCoords; // ВОзвращаем координаты открытого конца
    }

    // Определение выиграл ли символ
    public static boolean isVictorious(char side) {
        return isCharCountFound(side, WINCOUNT); // Победа если нашли победное кол-во символов подряд
    }

    // Ход игрока
    private static boolean humanTurn(Scanner in) {
        int xc = 0, yc = 0;

        while(true) {
            System.out.println();
            System.out.print("Введите координаты хода (2 числа от 1 до " + DIM + ") через пробел: ");
            String input = in.nextLine();
            String[] coords = input.split(" ");
            try {
                xc = Integer.parseInt(coords[0]);
                yc = Integer.parseInt(coords[1]);
                if (xc < 1 || xc > DIM || yc < 1 || yc > DIM) {
                    System.out.println("Некорректный ввод: нужны числа от 1 до " + DIM);
                    continue;
                }
                if (pitch[xc-1][yc-1] != EMPTY) {
                    System.out.println("Некорректный ввод: поле занято");
                    continue;
                }
                break; // Введены корректные значения
            } catch (Exception e) {
                System.out.println("Некорректный ввод: должно быть 2 числа через пробел\n");
            }
        }

        xc--;
        yc--;
        pitch[xc][yc] = X;
        printPitch(); // Отрисовка поля

        return isVictorious(X); // Выиграл ли Х
    }



    // Ход компа
    private static boolean compTurn() throws InterruptedException {
        int[][] openC = new int[1][2]; // Координаты открытого конца

        System.out.println("\nДумаю...");

        // Ждем
        TimeUnit.MILLISECONDS.sleep(1000);

        // Анализируем сделанные ходы игрока - есть ли около выигрышные (незаблокированные) комбинации
        // длиной от выигрыша-1 до 2
        // Если есть - блокируем первую самую протяженную комбинацию игрока

        for (int a = WINCOUNT-1; a > 1; a--) {
            openC = findOpenCharCount(X, a);
            if (openC[0][0] != -1) {
                pitch[openC[0][0]][openC[0][1]] = O;
                printPitch(); // Отрисовка поля
                return isVictorious(O); // Выиграл ли O
            }
        }
        // Если нет - ставим О в следующее свободное поле
                for (int i = 0; i < DIM; i++) {
                    for (int j = 0; j < DIM; j++) {
                        if(pitch[i][j] == EMPTY){
                            pitch[i][j] = O;
                            printPitch(); // Отрисовка поля
                            return isVictorious(O);  // Выиграл ли O
                        }
                    }
                }
            return isVictorious(O);  // Выиграл ли O
    }

    // Игра
    public static String tttGame(Scanner in) throws InterruptedException {

        int curTurn = 0;

        initPitch();
        printPitch();

        while(true) {
            boolean humanWon = humanTurn(in);
            if(humanWon) {
                return "вы победили!";
            }
            curTurn++;
            if(curTurn==MAXTURNS){
                return "ничья!";
            }
            boolean compWon = compTurn();
            if(compWon) {
                return "комп победил!";
            }
            curTurn++;
            if(curTurn==MAXTURNS){
                return "ничья!";
            }
            }
        }


    public static void main(String[] args) throws InterruptedException {
        String result;
        Scanner in = new Scanner(System.in);
        System.out.println("Игра Крестики-Нолики");
        result = tttGame(in);
        System.out.println("\nРезультат: " + result);
        System.out.println("Пока-пока");
    }
}
