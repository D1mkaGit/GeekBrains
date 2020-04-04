package lesson_2.dz;

import java.util.ArrayList;
import java.util.Random;

/**
 * 1. Создать массив большого размера (миллион элементов).
 * 2. Написать методы удаления, добавления, поиска элемента массива.
 * 3. Заполнить массив случайными числами.
 * 4. Написать методы, реализующие рассмотренные виды сортировок, и проверить скорость выполнения каждой.
 */
public class BigArray {
    private final ArrayList<Integer> arr;
    private int size = 1000000;

    private BigArray() {
        this.arr = new ArrayList<>(size);
    }

    public static void main(String[] args) {
        BigArray bigArr = new BigArray(); // Создать массив большого размера (миллион элементов)
        bigArr.fillWithRndNumbers(); // Заполнить массив случайными числами

        // негативная проверка
        bigArr.displayById(1999999);
        bigArr.deleteByArrElementId(1999999); // удаление по id
        bigArr.deleteByArrValue(1999999);
        bigArr.displayById(1999999);

        // добавление данных
        bigArr.insert(1999999, -1); //добавление числав конец массива
        bigArr.displayById(bigArr.getSize() - 1); // смотрим последний элемнт
        bigArr.insert(2999999, 500); //добавление числав по id
        bigArr.displayById(500); // смотрим последний добавленный элемент
        bigArr.displayByValue(1999999);

        // удаление данных
        bigArr.deleteByArrElementId(500); // удаление по id
        bigArr.deleteByArrValue(1999999); //удаление по значению
        bigArr.displayByValue(1999999);
        bigArr.displayByValue(2999999);
        bigArr.displayById(500);

        // вывод всего массива
        bigArr.display();
    }

    public int getSize() {
        return size;
    }

    private void display() {
        for (int i = 0; i < this.size; i++) {
            System.out.println(i + 1 + ": " + this.arr.get(i));
        }
    }

    private void displayById(int elementID) {
        if (findByArrId(elementID))
            System.out.println("Element with is: " + elementID + " is " + this.arr.get(elementID));
        else
            System.out.println("Element with id: " + elementID + " not found");
    }

    private void displayByValue(int elementValue) {
        if (findByEnumeration(elementValue))
            System.out.println("Element with value: " + elementValue + " is found");
        else
            System.out.println("Element with value: " + elementValue + " not found");
    }

    private void fillWithRndNumbers() {
        Random randomNumber = new Random();
        for (int i = 0; i < this.size; i++) {
            this.arr.add(i, randomNumber.nextInt(size));
        }
    }

    private boolean findByEnumeration(int value) {
        for (int i = 0; i < this.size; i++) {
            if (this.arr.get(i) == value) return true;
        }
        return false;
    }

    private boolean findByArrId(int value) {
        if (value >= 0 && value < this.size) {
            int low = 0;
            int high = this.size - 1;
            int mid;
            while (low <= high) {
                mid = (low + high) / 2;
                if (mid == value)
                    return true;
                if (value < mid)
                    high = mid - 1;
                else
                    low = mid + 1;
            }
        }
        return false;
    }

    public void deleteByArrValue(int value) {
        if (findByEnumeration(value)) {
            System.out.println("Element with value: " + value + " will be erased");
            moveElementsDuringDelete(value);
        } else {
            System.out.println("Element with value: " + value + " can't be erased");
        }
    }

    private void deleteByArrElementId(int elementId) {
        if (findByArrId(elementId)) {
            System.out.println("Element " + this.arr.get(elementId) + " with id: " + elementId + " will be erased");
            moveElementsDuringDelete(elementId);
        } else {
            System.out.println("Element with id: " + elementId + " can't be erased");
        }
    }

    /**
     * Insert values into array
     * @param value - value to be inserted
     * @param id    - array id, if id less than 0,value will be inserted to the end of array
     */
    private void insert(int value, int id) {
        this.size++;
        this.arr.add(this.size - 1, value);
        if (id >= 0 && id < this.size) {
            for (int i = this.size - 1; i > id; i--) {
                this.arr.set(i, this.arr.get(i - 1));
            }
            this.arr.set(id, value);
        }
    }

    private void moveElementsDuringDelete(int elementId) {
        for (int i = elementId; i < this.size - 1; i++) {
            this.arr.set(i, this.arr.get(i + 1));
        }
        this.size--;
    }
}
