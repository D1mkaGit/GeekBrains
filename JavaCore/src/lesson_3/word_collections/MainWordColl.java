package lesson_3.word_collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 1. Создать массив с набором слов (10-20 слов, среди которых должны встречаться повторяющиеся).
 * Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
 * Посчитать, сколько раз встречается каждое слово.
 */

public class MainWordColl {

    public static void main(String[] args) {
        String[] words = prepareString("Создать массив с набором слов (10-20 слов, среди которых должны " +
                "встречаться повторяющиеся). " +
                "Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем). Посчитать," +
                " сколько раз встречается каждое слово.");

        Map<String, Integer> hm = new HashMap<>();

        for (String subStr : words) {
            Integer res = hm.get(subStr);
            hm.put(subStr, res == null ? 1 : res + 1);
        }

        Set<Map.Entry<String, Integer>> set = hm.entrySet();

        System.out.println("Следующие слова встречаются в тексте, следующее количество раз:");
        int maxCount = 0;
        for (Map.Entry<String, Integer> me : set) {
            System.out.println("\"" + me.getKey() + "\" - " + me.getValue());
        }
    }

    private static String[] prepareString(String str) {
        String result = str
                .replaceAll("[().,]", "") // Убираем скобки, точки и запятые
                .toLowerCase(); // переводим символы в нижний регистр
        return result.split("\\s"); // Разбиение строки на слова с помощью разграничителя (пробел)
    }
}
