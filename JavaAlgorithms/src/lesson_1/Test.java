package lesson_1;

public class Test {
//    public static void main(String[] args) {
//        System.out.println(1%2 == 1);
//    }
//      public static void main(String[] args) {
//        {
//        int i = 20;
//        }
//        int i = 15;
//
//        System.out.println(i);
//        }
}

//1 Какой будет результат выполнения следующего кода
//
//
//        а) true
//        b) false
//        c) 1
//        d) Ошибка компиляции
//
//











//        2 Какой будет результат выполнения следующего кода
//
//        String str = "abc";
//
//        switch (str) {
//        case "ab":
//        System.out.println("ab");
//        case "abc":
//        System.out.println("abc");
//        default:
//        System.out.println("break");
//        case "abcd":
//        System.out.println("abcd");
//        }
//
//        а)  abc
//        b)  abc  break  abcd
//        c) abc  abcd
//        d) Ошибка компиляции
//
//
















//        3 Какой будет результат выполнения следующего кода
//
//        public static void main(String[] args) {
//        {
//        int i = 20;
//        }
//        int i = 15;
//
//        System.out.println(i);
//        }
//
//        а)  15
//        b)  20
//        c) Ошибка компиляции
//
//
//















//        4 Какой будет результат выполнения следующего кода
//

//class Test44 {
//    public static void main(String[] args) {
//        for (int i = 0; i < 5; i += 2) {
//            System.out.println(i++);
//        }
//    }
//}

//public static void main(String[] args) {
//        for (int i = 0; i < 5; i+=2) {
//        System.out.println(i++);
//        }
//        }
//
//        а)  0 2 4
//        b)  2 5
//        c) 0 3
//        d)  3
//













//        5 Какой будет результат выполнения следующего кода
//
//public static void main(String[] args) {
//        float d = 12.3;
//        System.out.println(d);
//        }
//
//        а)  12.3
//        b)  12
//        c) Ошибка при компиляции
//        d) Ошибка при выполнении
//














//        6 Какой будет результат выполнения следующего кода
//
//class Test66 {
//    public static void main(String[] args) {
//        int i = 2;
//
//        do {
//            System.out.println(++i);
//        } while (i == 3);
//    }
//}
//public static void main(String[] args) {
//        int i = 2;
//
//        do {
//        System.out.println(++i);
//        } while (i == 3);
//        }
//
//        а)  3 4
//        b)  3
//        c)  ничего
//        d)  Ошибка компиляции
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
//        7 Какой будет результат выполнения следующего кода
//
//public static void main(String[] args) {
//        int a = 1;
//        int b = 2;
//        c = a + b;
//        System.out.println(c);
//        }
//
//        а)  3
//        b)  "3"
//        c)  Ошибка компиляции
//
//


















//        8 Какой будет результат выполнения следующего кода
//
//public static void main(String[] args) {
//        char d2 = “Java”;
//        System.out.println(d2);
//        }
//
//
//        а)  Java
//        b)  d2
//        c)  Ошибка компиляции
//





















//        9 Какой будет результат выполнения следующего кода
//
//public static void main(String[] args) {
//        int x = 20;
//        String y = “my var: “;
//        x *= x;
//        System.out.println(x + y);
//        }
//
//        а)  400my var:
//        b)  my var 400
//        c)  Ошибка компиляции
//















//        10 Какой будет результат выполнения следующего кода
//
//public static void main(String[] args) {
//        System.out.println(9/0);
//        }
//
//        а)  Nan
//        b)  Ошибка при выполнении кода
//        c)  Ошибка компиляции












///////// 1
//interface I1 {
//    void copy() throws IOException;
//}
//
//interface I2 {
//    void copy() throws FileNotFoundException;
//}
//
//
//class A implements I1, I2 {
//
//    // вопрос: какие исключения нужно использовать и нужно ли?
//    @Override
//    public void copy() {
//
//    }
//}














//////////// 2

class Main {
    public static void main(String[] args) {
        System.out.print(Values.A);
    }
}

enum Values {
    A(1), B(2), C(3);

    Values(int i) {
        System.out.print(i);
    }

    static  {
        System.out.print("static");
    }
}












////////// какой будет результат


class equalsLong {
    public static void main(String[] args) {
        Long a = 120L;
        Long b = 120L;
        Long c = 222L;
        Long d = 222L;

        System.out.println(a == b);
        System.out.println(c == d);
    }
}











class Test4{
//    public static void main(String[] args) {
//            System.out.println(11/0);
//    }

//   public static void main(String[] args) {
//        double d1 = 11./0;
//        double d2 = 15./0;
//        System.out.println(d1);
//        System.out.println(d2);
//        System.out.println(d1 - d2);
//   }
}

//
//        public static void main(String[] args) {
//            System.out.println(11/0);
//        }

















//
//        public static void main(String[] args) {
//            double d1 = 11./0;
//            double d2 = 15./0;
//            System.out.println(d1);
//            System.out.println(d2);
//            System.out.println(d1 - d2);
//        }