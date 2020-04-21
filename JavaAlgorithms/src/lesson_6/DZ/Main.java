package lesson_6.DZ;

import java.util.Random;

public class Main {
    public static void main( String[] args ) {
//        MyTreeMap<Integer, String> mtm = new MyTreeMap<>();
//
//        mtm.put(5, "five");
//        mtm.put(3, "tree");
////        mtm.put(1, "one");
//        mtm.put(4,"four");
//        mtm.put(2,"two");
//        mtm.put(6, "6");
//        mtm.put(6,"66");
//        mtm.put(7,"7");

//        System.out.println(mtm.get(2));
//        mtm.put(2,"two2222");

//        System.out.println(mtm.get(2));
//        System.out.println(mtm);
//
//        System.out.println(mtm.height());
        //       System.out.println(mtm.isBalance());

//        mtm.delete(3);
        //       System.out.println(mtm);
        testBalance();

    }

    static void testBalance() {
        int balanced = 0;
        Random random = new Random();
        MyTreeMap<Integer, String>[] treeMaps = new MyTreeMap[10000];
        for (int i = 0; i < treeMaps.length; i++) {
            treeMaps[i] = new MyTreeMap<>();
            while (treeMaps[i].height() < 5) {
                treeMaps[i].put(random.nextInt(200), "");
            }
            if (treeMaps[i].isBalance()) {
                balanced++;
            }
        }
        System.out.println("\n" + balanced);
    }
}
