package lesson_2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainEx {
    public static void main(String[] args) {
//        a();

//        int a,b;
//
//        try {
//            int[] r = {1,2,3};
//            a = 0;
//            b = 10/a;
//            r[20] = 15;
//            System.out.println("con");
//        }catch (Exception e) {
//            System.out.println(1);
//        }
////        try {
////            Thread.sleep(1000);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//        System.out.println("Завершение работы!");

//        try {
//            FileInputStream fileInputStream = new FileInputStream("1.txt");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

//        throw new RuntimeException("test");

//        System.out.println("1");

//        try {
//            sqrt(-10);
//        } catch (ArithmeticException e) {
//            e.printStackTrace();
////            try {
////                Thread.sleep(1000);
////            } catch (InterruptedException e1) {
////                e1.printStackTrace();
////            }
//            System.out.println("что-то делаем!");
//        }


//        System.out.println(doSomThing());


//        FileInputStream fileInputStream = null;
//        try {
//            fileInputStream = new FileInputStream("1.txt");
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            fileInputStream.close();
//        }

//        try(FileInputStream fileInputStream = new FileInputStream("1.txt")) {
//            fileInputStream.read();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try {
//            doSomThing();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }


    public static void doSomThing() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("1.txt");
        System.out.println("1");
    }


//    public static int doSomThing() {
//        try {
//            return 1;
//        } finally {
//            return 2;
//        }
//    }

    public static int sqrt(int n) {
        if (n > 0) {
            return n / 2;
        }
        throw new ArithmeticException("нельзя использовать отрицательное число!");
    }

//    public static void a() {
//        b();
//    }
//
//    public static void b() {
//        c();
//    }
//
//    public static void c() {
//        int a = 0;
//        int b = 10 / a;
//    }
}
