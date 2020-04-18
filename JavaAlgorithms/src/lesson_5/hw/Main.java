package Lesson_5.hw;

public class Main {
    public static void main(String[] args) {

        // Возведения в степень
//        System.out.println("task1");
//        System.out.println(" 2 ^ 3 = " + exponentiation(2, 3));
//        System.out.println(" 2 ^ 0 = " + exponentiation(2, 0));
//        System.out.println(" 5 ^ 2 = " + exponentiation(5, 2));
//        System.out.println("-5 ^ 3 = " + exponentiation(-5, 3));
//        System.out.println("-4 ^ 2 = " + exponentiation(-4, 2));
//        System.out.println(" 6 ^ -1 = " + exponentiation(6, -1));
//        System.out.println(" 3 ^ -3 = " + exponentiation(3, -3));
//        System.out.printf(" 10 ^ -3 = %f \n", exponentiation(10, -3));

        // Задача о рюкзаке

//        W=13,N=5
//        w1=3,p1=1
//        w2=4,p2=6
//        w3=5,p3=4
//        w4=8,p4=7
//        w5=9,p5=6
//        Таким образом, в набор входит 2 и 4 предмет.
//        Стоимость рюкзака: 6+7=13
//        Вес рюкзака: 4+8=12

        Item[] arrOfItems = {new Item(1, 3),
                new Item(6, 4),
                new Item(4, 5),
                new Item(7, 8),
                new Item(6, 9)};

        Bagpack bagpack = new Bagpack(arrOfItems);
        int bagpackCapacity = 13; // вместимость рюкзака
       // System.out.println("task2");
        System.out.println(bagpack.findBestSum(arrOfItems.length - 1, bagpackCapacity));
    }

    private static double exponentiation(double value, int power) {
        if (value == 0 && power <= 0) {
            throw new ArithmeticException("Неопределенность");
        }
        if (power == 0) {
            return 1;
        } else if (power < 0) {
            return 1 / value * exponentiation(value, ++power);
        } else {
            return value * exponentiation(value, --power);
        }
    }
}
