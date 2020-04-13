package lesson_5;

public class Test {
}



//
//
//class Test1 {
//    public static void main(String[] args) {
//        float f = 1.0 + 1.0f;
//        f = f + 1;
//        System.out.println( f / 0 );
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//class QTest {
//    {
//        System.out.print("1");
//    }
//
//    public static void main(String[] args) {
//        System.out.print("2");
//        new QTest();
//    }
//
//    static {
//        System.out.print("3");
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//class QTest{
//    public static void main(String[] args) {
//        int i1 = 012;
//        int i2 = 20;
//        System.out.println(i1 * i2);
//    }
//}
//
//
//        Какой результат выполнения данного кода:
//        int i1 = 012;
//        int i2 = 20;
//        System.out.println(i1 * i2);
//
//
//
//
//
//
//
//
//
//
//
//
//
//class Main123 {
//    public static void main(String[] args) {
//        Integer i = new Integer("10");
//        if (i.toString().intern() == i.toString().intern()) {
//            System.out.println("Равный");
//        } else {
//            System.out.println("Неравный");
//        }
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//class Foo {
//    public static void main(String[] args) {
//        for (int k = 1; k <= 10; k++) {
//            if (k % 5 == 0) break label;
//            System.out.print(k + " ");
//        }
//
//        label:
//        {
//            System.out.print("stop!");
//        }
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//class Funcs extends java.lang.Math {
//    public int add(int x, int y) {
//        return x + y;
//    }
//    public int sub(int x, int y) {
//        return x - y;
//    }
//    public static void main(String[] a) {
//        Funcs f = new Funcs();
//        System.out.println("" + f.add(1, 2));
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//class App1 {
//    public static void main(String[] args) {
//        System.out.println(addToString("12345678910",','));
//    }
//
//    public static StringBuffer addToString(String s, char c) {
//        StringBuffer b = new StringBuffer(s);
//        int p = 0;
//        for (int i = 1; i < b.length(); i++) {
//            if (i%3 == 0) {
//                b.insert(b.length()-i-p, c);
//                p++;
//            }
//        }
//        return b;
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//class Main123 {
//    public static void main(String[] args) {
//        System.out.println(args.length);
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
class Main56 {
    public static void main(String[] args) {
        int a = 10;
        int b = 100;
        double c = (double) (a/b);
        String str = String.format("%1.5f", c);
        System.out.println(str);
    }
}
