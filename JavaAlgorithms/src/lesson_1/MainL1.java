package lesson_1;

public class MainL1 {
    public static void main(String[] args) {
//        int a = 1;
//        testInt(a);
//        System.out.println(a);
//        /////////
//        String str = "abc";
//        testString(str);
//        System.out.println(str);
//        /////////
//        StringBuilder sb = new StringBuilder("abc");
//        testSb(sb);
//        System.out.println(sb);
//        //////////
//        int[] mass = {1,2,3};
//        testArray(mass);
//        System.out.println(Arrays.toString(mass));

//        int a = 10;
//        int b = 10;
//
//        if (a == b) {
//            System.out.println("равны");
//        }

//        Person p1 = new Person("name1");
//        Person p2 = new Person("name1");
//
//
//        if (p1.equals(p2)) {
//            System.out.println("равны");
//        } else {
//            System.out.println("не равны");
//        }

        //int[] mass1 = new int[3];
        //int array[] = new int[3]{4,5,6};

        //int[] mass = {1,2,3};


//        int n = 100;
//        System.out.println(n);
//        System.out.println(n);
//        System.out.println(n);

//        int n = 8;
//
//        for (int i = 1; i < n; i = i * 2) {
//            System.out.println(i);
//        }

//        int n = 8;
//        for (int i = 0; i < n; i++) {
//            System.out.println(i);
//        }

//        int n = 8;
//        for (int i = 1; i <= n ; i++) {
//            for (int j = 1; j < 8 ; j = j * 2) {
//                System.out.println(i + " " + j);
//            }
//        }

//        int n = 8;
//        for (int i = 1; i < factorial(n) ; i++) {
//            System.out.println(i);
//        }

    }

    static void testInt(int a) {
        a++;
    }

    static void testString(String str) {
        str += "z";
    }

    static void testSb(StringBuilder sb) {
        sb.append("z");
    }

    static void testArray(int[] mass) {
        mass[1] = 10;
    }

}
