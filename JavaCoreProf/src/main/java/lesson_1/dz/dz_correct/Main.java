package Lesson_1.DZ;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Box<Fruit> box = new Box<>();
        Box<Apple> boxa = new Box<>();
        boxa.addFruit(new Apple());
        boxa.transfer(box);
    }

    public static void swap(Object[] arr, int x1, int x2) {
        Object temp = arr[x1];
        arr[x1] = arr[x2];
        arr[x2] = temp;
    }

    public static <T> ArrayList<T> convert(T[] arr) {
        return new ArrayList<T>(Arrays.asList(arr));
    }
}