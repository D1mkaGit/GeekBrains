package lesson_7;

import java.util.Scanner;


public class Main {

    public static void main( String[] args ) {
        int k = 1;
        int direction = 0;
        int iteration = 0;

        Scanner scanner = new Scanner(System.in);

        int SIZE_X = scanner.nextInt();
        int SIZE_Y = scanner.nextInt();

        // static int SIZE_X=10, SIZE_Y=5;
        int arr[][] = new int[SIZE_Y][SIZE_X];


        for (int n = 0; k <= SIZE_X * SIZE_Y; n++) {
            direction = n % 4;
            iteration = n / 4;
            switch (direction) {
                case 0:
                    for (int j = iteration; j < SIZE_X - iteration; j++, k++) {
                        arr[iteration][j] = k;
                    }
                    break;
                case 1:
                    for (int i = iteration + 1; i < SIZE_Y - iteration; i++, k++) {
                        arr[i][SIZE_X - iteration - 1] = k;
                    }
                    break;
                case 2:
                    for (int j = SIZE_X - iteration - 2; j >= iteration; j--, k++) {
                        arr[SIZE_Y - iteration - 1][j] = k;
                    }
                    break;
                case 3:
                    for (int i = SIZE_Y - iteration - 2; i > iteration; i--, k++) {
                        arr[i][iteration] = k;
                    }
                    break;
            }
        }

        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                if (arr[i][j] < 10) System.out.print(" ");
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
