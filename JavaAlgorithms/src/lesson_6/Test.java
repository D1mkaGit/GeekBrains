package lesson_6;

public class Test {

}

///////////////// 1

//    Что произойдет в результате попытки компиляции и выполнения программы?
//class TreeTest {
//    private static Set<String> set = new TreeSet<String>();
//    public static void main(String[] args) {
//        set.add("one");
//        set.add("two");
//        set.add("three");
//        set.add("1");
//        set.add("2");
//        set.add("3");
//        for (String string : set) {
//            System.out.print(string + " ");
//        }
//    }
//}




///////////////// 2




// Что произойдет при попытке скомпилировать и запустить данный код?
// Считается, что файл outstream уже создан.
//
//class Main {
//    public static void main(String[] args) {
//        try {
//            FileInputStream fis = new FileInputStream("outstream");
//            InputStreamReader isr = new InputStreamReader(fis);
//            BufferedReader br = new BufferedReader(isr);
//            try {
//                do {
//                    String str = br.readLine();
//                    System.out.println(str);
//                } while (str != null);
//            }
//            finally {
//                fis.close();
//                isr.close();
//                br.close();
//            }
//        } catch(IOException e){
//            e.printStackTrace();
//        }
//    }
//}





///////////////// 3
// Что будет напечатано в результате компиляции и выполнения данного кода?
//
//class TestClass {
//    public static void main(String[] args) {
//        Base sub = new Sub();
//        sub.test();
//    }
//}
//
//class Base {
//    public static void test() {
//        System.out.println("Base.test()");
//    }
//}
//
//class Sub extends Base {
//    public static void test() {
//        System.out.println("Sub.test()");
//    }
//}
//








///////////////// 4
//Что будет выведено в консоль?

//class A
//{
//    {
//        System.out.print("5");
//    }
//
//    static {
//        System.out.print("3");
//    }
//
//    public A() {
//        System.out.print("4");
//    }
//}
//
//class B extends A {
//    {
//        System.out.print("2");
//    }
//
//    static {
//        System.out.print("6");
//    }
//
//    public B() {
//        System.out.print("1");
//    }
//
//    public static void main(String[] args) {
//        new B();
//    }
//}






///////////////// 5
//class Tasks {
//    public static Tasks instance = new Tasks();
//    private static final int DELTA = 5;
//    private static int BASE = 7;
//    private int x;
//
//    public Tasks() {
//        x = BASE + DELTA;
//    }
//    public static int getBASE() {
//        return BASE;
//    }
//    public static void main(String[] args) {
//        System.out.println(Tasks.instance.x);
//    }
//}





///////////////// 6
//Что напечатает следующий фрагмент кода:
//try {
//int i = 5;
//} catch (Exception e) {
//System.out.print("catch");
//} finally {
//System.out.print("finally");
//}












///////////////// 7

//Скомпилируется ли следующий код:
//public abstract class One {
//    public abstract int doJob();
//}
//class Two extends One { }
//












///////////////// 8
// Что напечатает следующий код?
//class TestWrap {
//    public static void main(String[] args) {
//        Integer ii = 1000;
//        Integer jj = 2000;
//
//        System.out.print((ii * 2 == jj) ? "yes " : "no ");
//        System.out.print((jj / 2 == ii) ? "yes " : "no ");
//        System.out.print((ii * 2 == jj) ^ (jj / 2 == ii) ? "yes " : "no ");
//    }
//}








///////////////// 9
// Что будет выведено на экран после выполнения следующего кода?
class Test1 {
    public static void main(String[] args){
        int x = 1;
        Integer y = new Integer(x);
        int [] z = {x};

        func(x, y, z);

        System.out.print(x);
        System.out.print(y);
        System.out.println(z[0]);
    }

    static void func (int x, Integer y, int[] z) {
        x++;
        y++;
        z[0]++;
    }
}
