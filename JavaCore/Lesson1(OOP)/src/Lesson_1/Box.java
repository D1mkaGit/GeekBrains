package Lesson_1;

public class Box {
    String name;
    int weight;

    public Box(String _name, int weight) {
        name = _name;
        this.weight = weight;
    }

    void startTest(Tools tools) {
        tools.infoToolsAndBox(this);
    }
}

class MainBox {
    public static void main(String[] args) {
        Box box = new Box("box1", 10);
        Tools tools = new Tools("hummer");

        box.startTest(tools);
    }
//    public static void main(String[] args) {
////        Box box1 = new Box("red", "name1", 10);
////        Box box2 = new Box("black", "name2", 20);
////
////        box1 = box2;
////
////        box1.info();
////        box2.info();
//
//       // int[] mass = new int[]{1,2, 10, 50, 11, 60};
//      //  calc("str1", 1,2, 10, 50, 11, 60);
////        MainBox mainBox = new MainBox();
////        mainBox.abc();
//       // abc();
//    }

    public void abc() {
        System.out.println("test");
    }


//    public static void calc(int a, int b) {
//        System.out.println(a + b);
//    }
//
//    public static void calc(int a, int b, int c) {
//        System.out.println(a + b + c);
//    }
//
//    public static void calc(int a, int b, int c, int d) {
//        System.out.println(a + b + c + d);
//    }
//
//    public static void calc(String s, int... a) {
//        int res = 0;
//
//        for (int i = 0; i < a.length; i++) {
//            res += a[i];
//        }
//
////        for (int c : a) {
////            res += c;
////        }
//
//        System.out.println(s + " "  + res);
//    }
}

class Tools {
    public Tools(String tools) {
        this.tools = tools;
    }

    String tools;
    void infoToolsAndBox(Box box) {
        System.out.println(tools + " "
                + box.name + " "
                + box.weight);
    }
}
