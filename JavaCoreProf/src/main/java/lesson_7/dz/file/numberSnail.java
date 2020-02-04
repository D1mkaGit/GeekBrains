package lesson_7.dz.file;

import java.util.Arrays;
import java.util.Scanner;

public class numberSnail {
    public static void main( String[] args ) {
        Scanner consc = new Scanner(System.in);
        System.out.println("Введите X размер матрицы: ");
        int xSize = consc.nextInt();
        System.out.println("Введите Y размер матрицы: ");
        int ySize = consc.nextInt();
        int steps = xSize * ySize;
        int a[][] = new int[xSize][ySize];
        int filler = 1, x = 0, y = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = 0;
            }
        }
        boolean up = false, down = false, left = false, right = true;
        while (steps != 0) {
            while (right) {
                if (y < a[x].length && a[x][y] == 0) {
                    a[x][y++] = filler++;
                    steps--;
                } else {
                    right = false;
                    down = true;
                    y--;
                    x++;
                }
            }
            while (down) {
                if (x < a.length && a[x][y] == 0) {
                    a[x++][y] = filler++;
                    steps--;
                } else {
                    down = false;
                    left = true;
                    x--;
                    y--;
                }
            }
            while (left) {
                if (y > -1 && a[x][y] == 0) {
                    a[x][y--] = filler++;
                    steps--;
                } else {
                    left = false;
                    up = true;
                    y++;
                    x--;
                }
            }
            while (up) {
                if (x > -1 && a[x][y] == 0) {
                    a[x--][y] = filler++;
                    steps--;
                } else {
                    right = true;
                    up = false;
                    x++;
                    y++;
                }
            }
        }
        for (int[] is : a) {
            System.out.println(Arrays.toString(is));
        }
    }
}


