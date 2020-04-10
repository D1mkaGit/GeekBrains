package Lesson_2.DZ;

import java.sql.*;
import java.util.Random;

public class Main {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement pstmt;

    private int size;
    int[] array;

    public Main(int size) {
        this.size = 0;
        this.array = new int[size];
    }

    public int fillRandom(int bound) {
        Random rnd = new Random();
        return rnd.nextInt(bound);
    }

    // заполнение массива по умолчанию
    public void add(int number) {
        array[this.size] = number;
        this.size++;
    }

    // удаление элементов по значению, через метод поиска индекса
    public void delete(int number){
        // идем дальше если find нашел в массиве нужный элемент
        if(find(number) != -1) {
            // запрашиваем индекс элемента и удаляем его
            for (int i = find(number); i < this.size - 1; i++) {
                this.array[i] = this.array[i + 1];
            }
            this.size--;
            // проверяем наличие элемента в оставшемся массиве, при наличии в массиве используем рекурсию
            for (int i = 0; i < this.size; i++) {
                if (this.array[i] == number) delete(number);
            }
        }
    }

    // возвращаем индекс элемента в массиве по искомому значению
    public int find(int findNumber) {
        int i;
        for (i = 0; i < this.size; i++) {
            if (this.array[i] == findNumber) return i;
        }
        return -1;
    }

    // выводим массив
    public void info() {
        for (int i = 0; i < this.size; i++) {
            System.out.print(this.array[i] + " ");
        }
        System.out.println();
        System.out.println("Размер массива = " + this.size);
        System.out.println("Элементов удалено = " + (array.length - this.size));
    }


    public static void main(String[] args) throws SQLException {

        long time = System.currentTimeMillis();
        Main obj = new Main(50000);
        for (int i = 0; i < obj.array.length; i++) {
            obj.add(obj.fillRandom(5));
        }
        obj.info();
        obj.delete(0);
        obj.info();
        System.out.println("Время выполнения = " + (System.currentTimeMillis() - time) + " мс.");

//        new Thread(null, new Runnable() {
//            public void run() {
//                long time = System.currentTimeMillis();
//                Main obj = new Main(50000);
//                for (int i = 0; i < obj.array.length; i++) {
//                    obj.add(obj.fillRandom(3));
//                }
//                obj.info();
//                obj.delete(0);
//                obj.info();
//                System.out.println("Время выполнения = " + (System.currentTimeMillis() - time) + " мс.");
//            }
//        }, "1", 1 << 23).start();

    }

}
