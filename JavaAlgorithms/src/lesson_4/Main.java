package lesson_4;

import java.util.HashMap;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
//        LinkedList<String> ll = new LinkedList<>();
////
////        ll.add("A");
////        ll.add("B");
////        ll.add("C");
////        ll.add("D");
////
////        ll.get(2);
////
////     //   ll.removeLast()
////
////        MyLinkedList<String> mll = new MyLinkedList<>();
////        mll.insertFirst("Bob1");
////        mll.insertFirst("Bob2");
////        mll.insertFirst("Bob3");
////
////        System.out.println(mll);
////
////        System.out.println(mll.removeFirst());
////        System.out.println(mll);

//        Map<String, Integer> hm = new HashMap<>();
//        hm.put("Васька", 5);
//        hm.put("Мурзик", 3);
//        hm.put("Барсик", 7);
//        hm.put("Васька", 77);
//
//        System.out.println(hm);


//        Random random = new Random();
//        Map<Integer, Integer> hm = new HashMap<>();
//
//        for (int i = 0; i < 100; i++) {
//            int num = random.nextInt(10);
//            Integer current = hm.get(num);
//            hm.put(num, current == null ? 1 : current + 1);
//        }
//
//        System.out.println(hm);

           int id = 771;
           HashMap<Integer, LinkedList<String>> map = init();
        System.out.println(map.getOrDefault(id, new LinkedList<String>()));

    }

    static HashMap<Integer, LinkedList<String>> init() {
        HashMap<Integer, LinkedList<String>> map = new HashMap<>();
        map.put(770, new LinkedList<String>());
        map.put(771, new LinkedList<String>());
        return map;
    }
}
