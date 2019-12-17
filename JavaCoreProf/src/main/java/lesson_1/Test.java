package lesson_1;

public class Test {
}


// 1

//class MainThree {
//    public static void main(String[] args) {
//        int i = 0;
//        System.out.println(++i == i++);
//        System.out.println(i);
//        System.out.println(i++ == i++);
//        System.out.println(i);
//    }
//}
//


// 2 Являются ли массивы в java однородными


// 3 В каком порядке должны размещаться следующие опертаторы


// 1 вариант
// Package statement
// Imports
// Class or interface definitions


// 2 вариант
// Imports
// Package statement
// Class or interface definitions

// 3 вариант
// в любом порядке


// 4
//class Test4{
//        public static void main(String[] args) {
//        String str = "abc";
//
//        switch (str) {
//            case "ab":
//                System.out.println("ab");
//            case "abc":
//                System.out.println("abc");
//            default:
//                System.out.println("break");
//            case "abcd":
//                System.out.println("abcd");
//        }
//    }
//
//}


//class Test5 {
//    public static void main(String[] args) {
//        for (int i = 0; i < 5; i += 2) {
//            System.out.println(i++);
//        }
//    }
//}


// 5
//    public static void main(String[] args) {
//        for (int i = 0; i < 5; i+=2) {
//            System.out.println(i++);
//        }
//    }


// 6
//
class Test6 {
    public static void main(String[] args) {
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