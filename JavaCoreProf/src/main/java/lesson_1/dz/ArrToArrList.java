package lesson_1.dz;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrToArrList<T> {
    private T[] array;

    public ArrToArrList(T[] array) {
        this.array = array;
    }

    public T[] getArray() {
        return array;
    }

    public void setArray(T[] array) {
        this.array = array;
    }

    public ArrayList<T> arrToList() {
        return new ArrayList<>(Arrays.asList(array));
    }
}

class Main {
    public static void main(String[] args) {
        String[] strArr = {"Вася", "Петя", "Оля", "Наташа"};
        ArrayList arrList = new ArrayList<>(Arrays.asList(strArr));
        System.out.print(arrList.get(0));
        for (int i = 1; i < arrList.size(); i++) {
            System.out.print(", " + arrList.get(i));
        }
        System.out.println("\n");
    }
}
