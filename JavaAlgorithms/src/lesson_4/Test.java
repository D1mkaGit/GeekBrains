package lesson_4;

public class Test {
}
// 1 --------------------------
class Mountain {
    static String name = "Himalaya";

    static Mountain getMountain() {
        //System.out.println("Getting Name ");
        return null;
    }

    public static void main(String[ ] args) {
        System.out.println( getMountain().name );
        System.out.println( Mountain.name );
    }
}
// -----------------------------------

















// 2 ---------------------
//class Test2 {
//    static void method(int... a) {
//        System.out.println("inside int...");
//    }
//    static void method(long a, long b) {
//        System.out.println("inside long");
//    }
//    static void method(Integer a, Integer b) {
//        System.out.println("inside INTEGER");
//    }
//    public static void main(String[] args) {
//        int a = 2;
//        int b = 3;
//        method(a,b);
//    }
//}
// ------------------------------














// 3 ---------------------
//class Test3 {
//    public static void main(String[] args) {
//        doIt("test");
//    }
//    public static void doIt(String String) {
//        int i = 10;
//        i : for (int k = 0 ; k< 10; k++) {
//            System.out.println( String + i);
//            if( k*k > 10) continue i;
//        }
//    }
//}
// ------------------------





















// 4
//-------------------------------------
//    public static void main(String[] args) {
//        Box box1 = new Box(10, "red");
//        Box box2 = new Box(10, "red");
//        System.out.println(box1 == box2);
//        System.out.println(box1.equals(box2));
//    }
//-------------------------------------





//class Box {
//    int weight;
//    String color;
//
//    public Box(int weight, String color) {
//        this.weight = weight;
//        this.color = color;
//    }
//}














// 5
//
//class MainThree {
//    public static void main(String[] args) {
//        int i = 0;
//        System.out.println(++i);
//        System.out.println(i++ + " " + i++);
//
//        System.out.println(++i == i++);
//        System.out.println(i++ == i++);
//    }
//}
