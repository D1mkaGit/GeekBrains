package lesson_8.my_dz;

import lesson_8.ChainingHashMap;

public class ChainingHashMapApp {
    public static void main(String[] args) {
        ChainingHashMap<Integer, String> map = new ChainingHashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(12, "12");
        map.put(15, "15");

        checkKey(map,1);
        System.out.println("Удаляем по значению = " + map.deleteByValue("one"));
        checkKey(map,1);

        checkKey(map,2);
        System.out.println("Удаляем значение: " + map.delete(2) + " по ключу");
        checkKey(map,2);

        //System.out.println(map.get(2));
        //System.out.println(map);

//        Random random = new Random();
//        for (int i = 0; i < 50; i++) {
//            map.put(random.nextInt(1000), "");
//        }
//        System.out.println(map);
    }

    public static void checkKey(ChainingHashMap<Integer, String> map, int key) {
        System.out.println("Есть ли " + key + " ключ? " + (map.get(key) != null));
        System.out.println("Проверяем по " + key + " ключу значение: " + map.get(key));
    }
}
