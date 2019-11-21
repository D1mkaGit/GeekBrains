package lesson_3;

import java.util.TreeSet;

public class MainColl {
    public static void main(String[] args) {
        //int[] mass = new int[5];

//        ArrayList<Integer> ai = new ArrayList<>();
//        ai.ensureCapacity(10000);
//        System.out.println(ai.size());
//        ai.add(1);
//        ai.add(2);
//        ai.add(2);
//        ai.add(4);
//        ai.add(null);
//        System.out.println(ai.size());
//        System.out.println(ai);
//        ai.trimToSize();
//
//        ai.remove((Integer) 2);
//        System.out.println(ai);
//
//        if (ai.contains(2)) {
//            System.out.println("содержит!");
//            System.out.println(ai.get(1));
//        }


//        ArrayList<String> states = new ArrayList<>();
//        states.add("Германия");
//        states.add("Германия");
//        states.add("Франция");
//        states.add("Италия");
//        states.add("Испания");
//        states.add("Россия");
//
//        Iterator<String> iter = states.iterator();
//
//        while (iter.hasNext()) {
//            if (iter.next().equalsIgnoreCase("Германия")) {
//                iter.remove();
//            }
//        }

//        for (int i = 0; i < states.size(); i++) {
//            if (states.get(i).equalsIgnoreCase("Германия")) {
//                states.remove(i);
//                i--;
//            }
//        }

//        System.out.println(states);

//        LinkedList<String> ll = new LinkedList<>();
//
//        ll.add("a");
//        ll.addFirst("b");
//        ll.addLast("z");
//
//        ll.remove(2);
//
//        System.out.println(ll);

//        Random random = new Random();
//        Map<Integer, Integer> hm = new HashMap<>();
//
//        for (int i = 0; i < 100; i++) {
//            int num = random.nextInt(10);
//            Integer res = hm.get(num);
//            hm.put(num, res == null ? 1 : res + 1);
//        }
//
//        System.out.println(hm);

//        hm.put("Васька", 2);
//        hm.put("Васька", 3);
//        hm.put("Рыжик", 2);
//        hm.put("Барсик", 2);
//
//        System.out.println(hm);

//        Map<Integer, Book> hm = new HashMap<>();
//
//        Book book1 = new Book();
//        book1.title = "a";
//
//        Book book2 = new Book();
//        book2.title = "b";
//
//        hm.put(1, book1);
//        hm.put(2, book2);

//        Map<String, Integer> hm = new HashMap<>();
//        hm.put("asd", 1);
//        Set<Map.Entry<String, Integer>> set = hm.entrySet();
//
//        for (Map.Entry<String, Integer> me: set) {
//            System.out.println(me.getKey() + " " + me.getValue());
//        }

//         HashSet<String> hs = new HashSet<>();
//
//         hs.add("Бетта");
//         hs.add("Альфа");
//         hs.add("Гамма");
//
//         String str = new String("asd");
//
//         str.hashCode();

        //System.out.println(hs + " " + str.hashCode());

        TreeSet<Book> ts = new TreeSet<>();
        ts.add(new Book());
        ts.add(new Book());
        ts.add(new Book());
        ts.add(new Book());

        System.out.println(ts);
    }
}


class Book {
    String title;
}