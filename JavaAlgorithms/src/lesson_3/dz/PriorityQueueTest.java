package lesson_3.dz;

import lesson_3.MyPriorityQueue;

public class PriorityQueueTest {
    public static void main( String[] args ) {
        MyPriorityQueue<Character> mpq = new MyPriorityQueue<>();
        String[] characters = {"a", "z", "g", "G", "W", "b"};
        StringBuilder charactersCombined = new StringBuilder();
        for (String s : characters)
            charactersCombined.append(s); // собираем стринг
        char[] stringToCharArray = charactersCombined.toString().toCharArray(); // бъем на чары
        for (int i = 0; i < characters.length; i++) {
            mpq.insert(stringToCharArray[i]);
            System.out.println(mpq);
        }

        System.out.println("Удаляем: " + mpq.remove());
        System.out.println("Остались: " + mpq);
        System.out.println("В отсортерованной очереди: " + mpq.size() + " элементов.");
    }
}
