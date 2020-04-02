package lesson_2.dz;

import java.util.Random;

/**
 * 1. Создать массив большого размера (миллион элементов).
 * 2. Написать методы удаления, добавления, поиска элемента массива.
 * 3. Заполнить массив случайными числами.
 * 4. Написать методы, реализующие рассмотренные виды сортировок, и проверить скорость выполнения каждой.
 */
public class BigArray {
    private final int[] arr;
    private final int size = 1000000;

    public static void main( String[] args ) {
        BigArray bigArr = new BigArray(); // Создать массив большого размера (миллион элементов)
        bigArr.fillWithRndNumbers(); //Заполнить массив случайными числами
        bigArr.display();
    }

    private BigArray() {
        this.arr = new int[size];
    }

    private void display() {
        for (int i = 0; i < this.size; i++) {
            System.out.println(i + 1 + ": " + this.arr[i]);
        }
    }

    private BigArray fillWithRndNumbers() {
        Random randomNumber = new Random();
        for (int i = 0; i < this.size; i++) {
            this.arr[i] = randomNumber.nextInt(size);
        }
        return this;
    }

    private boolean findByEnumeration( int value ) {
        for (int i = 0; i < this.size; i++) {
            if (this.arr[i] == value) return true;
        }
        return false;
    }

    private boolean binaryFind( int value ) {
        int low = 0;
        int high = this.size - 1;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (this.arr[mid] == value)
                return true;
            if (value < this.arr[mid])
                high = mid - 1;
            else
                low = mid + 1;
        }
        return false;
    }

    public void deleteByArrValue( int value ) {

    }

    public void deleteByArrElementId( int elementId ) {

    }
}
