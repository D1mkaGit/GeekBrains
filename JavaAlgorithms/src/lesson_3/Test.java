package lesson_3;

public class Test {
    // 1
    public static void main( String[] args ) {
        String strA = "text";
        String strB = "text";
        strA += "1";
        strB += "1";
        System.out.println(strA == strB);
        strA = "text1";
        strB = "text1";
        System.out.println(strA == strB);
    }
}


// 2
//interface I {
//    public final static int EASY = 5;
//}
//
//class Main2 implements I {
//    public static void main(String[] args) {
//        int a = 5;
//        test(++a);
//    }
//
//    static void test(int a) {
//        a += EASY + a++;
//        System.out.println(a);
//    }
//}


// 3  Какие интерфейсы хранят ключ-значение

// 1 Set
// 2 SortedMap
// 3 List
// 4 SortedSet
// 5 Map


// 4
//
//    Как можно уничтожить объект в Java?
//
//        1. присвоить null всем ссылкам на объект
//        2. вызвать Runtime.getRuntime().gc()
//        3. вызвать метод finalize() у объекта
//        4. этого нельзя сделать вручную
//        5. вызвать деструктор у объекта


// 5
//Перечислите все валидные сигнатуры конструкторов класса Clazz:
//
//        Clazz(String name)
//        Clazz Clazz(String name)
//        int Clazz(String name)
//        void Clazz(String name)
//        Clazz(name)
//        Clazz()


// 6 Что будет напечатано после попытки компиляции и выполнения кода:
//
//class Test6 {
//    public static void main(String[] args) {
//        int[] mass = {1, 2};
//        List<String> list = new ArrayList(10);
//        list.add("03");
//        list.add("04");
//        System.out.println("." + mass.length + list.size() + ( 2 + 2 + 2 ));
//    }
//}


//7) Можно ли описать конструкторы в абстрактном классе?
//abstract class A {
//    int p1;
//    A() {
//        p1 = 1;
//    }
//
//}
//class B extends A {
//}


// 8

//class TT {
//    private static int count = 0;
//    private final int id = ++count;
//
//    private void methodA () {
//        System.out.println("TT.methodA " + id);
//    }
//
//    public void methodTT(TT t) {
//        t.methodA();
//    }
//}
//
//
//class Test1 {
//    public static void main(String[] args) {
//        TT t1 = new TT();
//        TT t2 = new TT();
//        t2.methodTT(t1);
//    }
//}


// 9
//class TestClazz {
//    public static void main(String[] args) {
//        final long Byte = 0;            // 1
//        if ( Byte.equals(0) ) {        // 2
//            System.out.print("==");
//        } else {
//            System.out.print("!=");
//        }
//    }
//}

// 1       Возникнет ошибка компиляции в строке 1
// 2       Возникнет ошибка компиляции в строке 2
// 3       Код напечатает "=="
// 4       Код напечатает "!="
// 5       Код успешно скомпилируется