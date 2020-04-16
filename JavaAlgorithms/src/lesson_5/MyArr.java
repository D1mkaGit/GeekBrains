package lesson_5;

import java.util.Random;

class MyArr {
    private final int[] arr;
    private int size;
    private int arrKey;

    public MyArr( int size ) {
        this.size = 0;
        this.arr = new int[size];
    }

    public void binaryFind( int search ) {
        if (recBinaryFind(search, 0, size - 1)) {
            System.out.println("У первого значения " + search + " в массиве, номер индекса элемента равен: " + arrKey);
        } else {
            System.err.println("Значение " + search + " не найденно!");
        }
    }

    //3. Доработать бинарный рекурсивный поиск (если элемент найден, вернуть true + индекс элемента, если нет бросить исключение)
    private boolean recBinaryFind( int searchKey, int low, int high ) {
        int curIn;
        curIn = (low + high) / 2;
        if (arr[curIn] == searchKey) {
            this.arrKey = curIn;
            return true;
        } else if (low > high)
            return false;
        else {
            if (arr[curIn] < searchKey)
                return recBinaryFind(searchKey, curIn + 1, high);
            else
                return recBinaryFind(searchKey, low, curIn - 1);
        }
    }

    public void insert( int value ) {
        int i;
        for (i = 0; i < this.size; i++) {
            if (this.arr[i] > value)
                break;
        }
        if (this.size - i >= 0)
            System.arraycopy(this.arr, i, this.arr, i + 1, this.size - i);
        this.arr[i] = value;
        this.size++;
    }

    public void insertRandom( int maxValue ) {
        insert(getRandomNumber(maxValue));
    }

    private int getRandomNumber( int max ) {
        Random r = new Random();
        return r.nextInt((max - 1) + 1) + 1;
    }
}

class MyArrApp {
    public static void main( String[] args ) {
        int maxValue = 10;
        int arrSize = 20;
        MyArr arr = new MyArr(arrSize);
        for (int i = 0; i < arrSize; i++) {
            arr.insertRandom(maxValue);
        }

        int search = 10;

        arr.binaryFind(search);

    }
}
