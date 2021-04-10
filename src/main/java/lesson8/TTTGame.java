package lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TTTGame extends JFrame {
    public static char X = 'X';
    public static char O = 'O';
    public static char EMPTY = '.';

    public static int DIM = 3;      // Размерность поля
    public static int WINCOUNT = 3; // Символов подряд для выигрыша

    public static char[][] pitch = new char[DIM][DIM];
    public static int MAXTURNS = DIM*DIM; // Максимум ходов

    public static void initPitch() {
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                pitch[i][j] = EMPTY;
            }
        }
    }

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

    public static boolean isVictorious(char side) {
        return isCharCountFound(side, WINCOUNT); // Победа если нашли победное кол-во символов подряд
    }

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

    // Ход компа
    private void compTurn() throws InterruptedException {
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

                Container grid1 = this.getContentPane();
                Component[] buttons = grid1.getComponents();
                for (int i = 0; i < buttons.length; i++) {
                    if (!(buttons[i] instanceof JButton)) { continue; }
                    String[] coords = ((JButton)buttons[i]).getName().split(" ");
                    if (Integer.parseInt(coords[0])==openC[0][0]&&Integer.parseInt(coords[1])==openC[0][1]) {
                        try {

                            Image smallcircle = new ImageIcon(new URL("http://clipart-library.com/images_k/black-circle-png-transparent/black-circle-png-transparent-5.png")).
                                    getImage().
                                    getScaledInstance(90, 90, Image.SCALE_SMOOTH);

                            ((JButton)buttons[i]).setIcon(new ImageIcon(smallcircle));

                        } catch (MalformedURLException malformedURLException) {
                            malformedURLException.printStackTrace();
                        }
                    }
                }
                System.out.println(openC[0][0] + " " + openC[0][1] + " smart generated! " + pitch[openC[0][0]][openC[0][1]]);
                return;
            }
        }
        // Если нет - ставим О в следующее свободное поле
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                if (pitch[i][j] == EMPTY) {
                    pitch[i][j] = O;

                    Container grid1 = this.getContentPane();
                    Component[] buttons = grid1.getComponents();
                    for (int k = 0; k < buttons.length; k++) {
                        if (!(buttons[k] instanceof JButton)) { continue; }
                        String[] coords = ((JButton)buttons[k]).getName().split(" ");
                        if (Integer.parseInt(coords[0]) == i && Integer.parseInt(coords[1]) == j) {
                            try {

                                Image smallcircle = new ImageIcon(new URL("http://clipart-library.com/images_k/black-circle-png-transparent/black-circle-png-transparent-5.png")).
                                        getImage().
                                        getScaledInstance(90, 90, Image.SCALE_SMOOTH);

                                ((JButton) buttons[k]).setIcon(new ImageIcon(smallcircle));
                                System.out.println(i + " " + j + " generated! " + pitch[i][j]);
                                return;

                            } catch (MalformedURLException malformedURLException) {
                                malformedURLException.printStackTrace();

                            }
                        }
                    }
                }
            }
        }
    }


    int xc = 0, yc = 0;
    int curTurn = 0;

    public TTTGame() throws HeadlessException {

        initPitch();

        this.setBounds(100, 100, 300, 300);
        this.setResizable(false);
        this.setTitle("Tic Tac Toe game");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new GridLayout(DIM, DIM));
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                JButton button = new JButton();
                button.setName(i + " " + j);
                button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String[] coords = button.getName().split(" ");
                    xc = Integer.parseInt(coords[0]);
                    yc = Integer.parseInt(coords[1]);
                    if (pitch[xc][yc] != EMPTY) {
                        System.out.println("Некорректный ввод: поле занято");
                        return;
                    }
                    pitch[xc][yc] = X;
                    try {

                        Image smallcross = new ImageIcon(new URL("https://www.vhv.rs/dpng/d/416-4167052_cross-sign-png-tic-tac-toe-cross-transparent.png")).
                                getImage().
                                getScaledInstance(90, 90, Image.SCALE_SMOOTH);

                        button.setIcon(new ImageIcon(smallcross));

                    } catch (MalformedURLException malformedURLException) {
                        malformedURLException.printStackTrace();

                    }

                    System.out.println(xc + " " + yc + " pressed! " + pitch[xc][yc]);

                    if(isVictorious(X)) {
                        JOptionPane.showMessageDialog(getParent(), "Вы победили!");
                        System.out.println("вы победили!");
                        System.exit(0);
                    }
                    curTurn++;
                    if(curTurn==MAXTURNS){
                        JOptionPane.showMessageDialog(getParent(), "Ничья!");
                        System.out.println("ничья!");
                        System.exit(0);
                    }

                    try {
                        compTurn();
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }

                    if(isVictorious(O)) {
                        JOptionPane.showMessageDialog(getParent(), "Комп победил!");
                        System.out.println("комп победил!");
                        System.exit(0);
                    }
                    curTurn++;
                    if(curTurn==MAXTURNS){
                        JOptionPane.showMessageDialog(getParent(), "Ничья!");
                        System.out.println("ничья!");
                        System.exit(0);
                    }
                }
            });
            add(button);
            }
        }

        this.setVisible(true);
    }
}
