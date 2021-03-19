package lesson2;

public class lesson2Tasks {

    // Метод для печати целочисленного массива
    static void printIntArray(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // Метод для заполнения целочисленного массива случайными значениями
    static int[] fillRandomArray(int arr[], int limit) {
        for (int i = 0; i < arr.length; i++) {
                 arr[i] = (int) Math.round((Math.random() - Math.random()) * (limit+1));
        }
        return arr;
    }

    // Задание 1
    // Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
    // С помощью цикла и условия заменить 0 на 1, 1 на 0;
    static void replaceZerosAndOnes(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = 1;
            }
            else {
                arr[i] = 0;
            }
        };
    }
    // Задание 2
    // Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21
    static void fillArrayWith3FactorVals(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i*3;
        }
    }

    // Задание 3
    // Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2
    static void replaceMultBy2(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6 ) {
                arr[i]*=2;
            }
        }
    }

    // Задание 4
    // Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое)
    // и с помощью цикла(-ов) заполнить его диагональные элементы единицами
    static void fillDiagonalWithOnes(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (i == j || i+j == arr.length-1) {
                    arr[i][j] = 1;
                }
            }
        }
    }

    // Задание 5
    // Задать одномерный массив и найти в нем минимальный и максимальный элементы
    static int[] countMinMax(int[] arr) {
        int[] minMaxVals = new int[2];
        int minValue = arr[0], maxValue = arr[0];

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < minValue) {
                minValue = arr[i];
            }
            if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }

        minMaxVals[0] = minValue;
        minMaxVals[1] = maxValue;

        return minMaxVals;
    }

    // Задание 6
    // Метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true,
    // если в массиве есть место, в котором сумма левой и правой части массива равны.
    // Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true,
    // граница показана символами ||, эти символы в массив не входят.

    static boolean isBalancedSpot(int[] arr){
        int sumLeft = 0, sumRight = 0;

        for (int i = 0; i < arr.length; i++) {
            sumLeft = 0; sumRight = 0;
            // считаем сумму элементов левой части
            for (int jleft = 0; jleft < i; jleft++) {
                sumLeft+=arr[jleft];
            }

            // считаем сумму элементов правой части
            for (int jright = i; jright < arr.length; jright++) {
                sumRight+=arr[jright];
            }
            // есть ли баланс сумм элементов
            if (sumLeft == sumRight) {
                // если нашли
                return true;
            }
        }
        // если не нашли
        return false;
    }

    // Задание 7 - пока не получилось :)
    // Метод, которому на вход подается одномерный массив и число n (может быть положительным, или отрицательным),
    // при этом метод должен сместить все элементы массива на n позиций. Элементы смещаются циклично.
    // Для усложнения задачи нельзя пользоваться вспомогательными массивами.
    // Примеры:
    // [ 1, 2, 3 ] при n = 1 (на один вправо) -> [ 3, 1, 2 ];
    // [ 3, 5, 6, 1] при n = -2 (на два влево) -> [ 6, 1, 3, 5 ].
    // При каком n в какую сторону сдвиг можете выбирать сами

    /*
    // заменяем значение в массиве и возвращаем замененное значение
    static int replaceItem(int[] arr, int newValue, int index) {
        int oldValue = arr[index];
        arr[index] = newValue;
        return oldValue;
    }

    static int[] shiftArray(int[] arr, int n) {

        // Если смещение больше размерности массива - выходим
        if (n > arr.length) {
        return arr;
        }

        int val = arr[0];
        int idxReplace = 0;

        for (int i = 0; i < arr.length-1; i++) {

                idxReplace = n + i;
                val = arr[i];

            if (idxReplace>=arr.length){
                break;
            }
            val = replaceItem(arr, val, idxReplace);
            arr[i] = val;
        }
        return arr;
    }
*/



    public static void main(String[] args) {
        // вызов replaceZerosAndOnes (задание 1)
        int[] arrReplace = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};

        System.out.println("Task 1 initial array:");
        printIntArray(arrReplace);

        replaceZerosAndOnes(arrReplace);

        System.out.println("Task 1 result array:");
        printIntArray(arrReplace);

        System.out.println();

        // вызов fillArrayWith3FactorVals (задание 2)
        int[] arrFill3Factor = new int[8];
        System.out.println("Task 2 initial array:");
        printIntArray(arrFill3Factor);

        fillArrayWith3FactorVals(arrFill3Factor);

        System.out.println("Task 2 result array:");
        printIntArray(arrFill3Factor);

        System.out.println();

        // вызов fillArrayWith3FactorVals (задание 3)
        int[] arrMultBy2 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        System.out.println("Task 3 initial array:");
        printIntArray(arrMultBy2);

        replaceMultBy2(arrMultBy2);

        System.out.println("Task 3 result array:");
        printIntArray(arrMultBy2);

        System.out.println();

        // вызов fillDiagonalsWithOnes (задание 4)
        int[][] arrTwoDims = new int[20][20];

        System.out.println("Task 4 initial array:");
        for (int i = 0; i < arrTwoDims.length; i++) {
            printIntArray(arrTwoDims[i]);
        }

        fillDiagonalWithOnes(arrTwoDims);

        System.out.println("Task 4 result array:");
        for (int i = 0; i < arrTwoDims.length; i++) {
            printIntArray(arrTwoDims[i]);
        }

        // вызов countMinMax (задание 5)
        int[] arrRandom = new int[10];

        System.out.println();
        fillRandomArray(arrRandom, 100);

        System.out.println("Task 5 initial array:");
        printIntArray(arrRandom);

        System.out.println("Task 5 results:");
        int[] minMaxArr = countMinMax(arrRandom);
        System.out.println("Min = " + minMaxArr[0] + ", Max = " + minMaxArr[1]);

        System.out.println();

        // вызов hasBalancedSpot (задание 6)
        //int[] arrBalance  = {2, 2, 2, 1, 2, 2, 10, 1};
        //int[] arrBalance  = {1, 1, 1, 2, 1};
        int[] arrBalance = new int[20];
        fillRandomArray(arrBalance, 2);

        System.out.println("Task 6 initial array:");
        printIntArray(arrBalance);

        System.out.println("Array has balanced spot: " + isBalancedSpot(arrBalance));
        // Повезло :)
        // Task 6 initial array:
        // 2 0 0 -1 0 0 0 0 1 -1 -1 -1 |здесь баланс -1| 1 0 0 0 0 -1 1 -2
        // Array has balanced spot: true


/*         7 задание не получилось пока
        // вызов shiftArray (задание 7)
        //int[] arrForShift = new int[10];
        int[] arrForShift = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        System.out.println();
        //
        //fillRandomArray(arrForShift, 100);

        System.out.println("Task 7 initial array:");
        printIntArray(arrForShift);

        int[] arrShifted = shiftArray(arrForShift, 3);
        System.out.println("Task 7 result array:");
        printIntArray(arrShifted);
*/

    }
}
