package lesson_5;

public class Main {
    public static void main(String[] args) {
       // countdown(10);
//        Long t = System.currentTimeMillis();
//        System.out.println(fact(50));
//        System.out.println(System.currentTimeMillis() - t);

//        Long t = System.currentTimeMillis();
//        System.out.println(recFact(20));
//        System.out.println(System.currentTimeMillis() - t);
      //  hello("Sofia");

//        Long t = System.currentTimeMillis();
//        System.out.println(recFibo(3));
//        System.out.println(System.currentTimeMillis() - t);

      //  System.out.println(triangleNum(5));

      //  System.out.println(recTriangle(5));

     //   System.out.println(multiply(2,10));
        System.out.println(recMultiply(2,5));
    }

    static int recMultiply(int a, int b) {
        if (b == 1) {
            return a;
        }
        return recMultiply(a, b - 1) + a;
    }

    static int multiply(int a, int b) {
        int value = 0;
        for (int i = 0; i < b; i++) {
            value += a;
        }
        return value;
    }


    static int recTriangle(int n) {
        if (n <= 1) {
            return 1;
        }
        return recTriangle(n - 1) + n;
    }

    static int triangleNum(int n) {
        int sum = 0;
        for (int i = 1; i <= n ; i++) {
            sum += i;
        }
        return sum;
    }


    static long recFibo(int n) {
        System.out.print(n + " ");
        if (n < 3) {
            return 1;
        }

        return recFibo(n - 2) + recFibo(n - 1);
    }


    static long fibo(int n) {
        long a = 1;
        long b = 1;
        System.out.print(n + " ");

        for (int i = 3; i <= n ; i++) {
            b = b + a;
            a = b - a;
        }
        return b;
    }


    public static void hello(String name) {
        System.out.println("Hello " + name);
        bye(name);
    }

    public static void bye(String name) {
        System.out.println("Good bye " + name);
    }



    static Long recFact(int n) {
        if (n <= 1) {
            return 1L;
        }

        return recFact(n - 1) * n;
    }

    // factorial
    static Long fact(int n) {
        Long value = 1l;
        for (int i = 1; i <= n ; i++) {
            value *= i;
        }

        return value;
    }

//    public static int countdown(int n) {
//        System.out.println(n);
//        if ( n == 1) {
//            return 1;
//        }
//        return countdown(n - 1);
//    }
}
