package lesson_3.phonebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
 * В этот телефонный справочник с помощью метода add() можно добавлять записи.
 * С помощью метода get() искать номер телефона по фамилии.
 * Следует учесть, что под одной фамилией может быть несколько телефонов, тогда при запросе такой фамилии должны выводиться все телефоны.
 */

public class PhoneBook {
    private final Map<String, ArrayList<String>> phoneBookHashMap;
    private final Set<Map.Entry<String, ArrayList<String>>> set;

    public PhoneBook() {
        phoneBookHashMap = new HashMap<>();
        set = phoneBookHashMap.entrySet();
    }

    public PhoneBook add(String _lName, String _pNumber) {
        ArrayList<String> phones = new ArrayList<>();
        if (phoneBookHashMap.containsKey(_lName)) {
            for (Map.Entry<String, ArrayList<String>> me : set) {
                if (me.getKey().equals(_lName)) phones = me.getValue();
            }
        }
        phones.add(_pNumber);
        phoneBookHashMap.put(_lName, phones);
        return this;
    }

    public void get(String _lName) {
        int counter = 0;
        for (Map.Entry<String, ArrayList<String>> me : set) {
            if (me.getKey().equals(_lName)) {
                counter++;
                for (String phoneNumber : me.getValue()) {
                    System.out.println(me.getKey() + ": " + phoneNumber);
                }
            }
        }
        if (counter == 0) System.out.println("Вы искали:" + _lName + ". Такой фамилии нет в телефонной книге.");
    }

    public PhoneBook printPhoneBook() { // красивый вывод всей телефонной книги
        for (Map.Entry<String, ArrayList<String>> me : set) {
            for (String phoneNumber : me.getValue()) {
                System.out.println(me.getKey() + ": " + phoneNumber);
            }
        }
        return this;
    }

    public void printRawPhoneBook() {
        System.out.println(phoneBookHashMap); // выводим весь хэшмэп как есть
    }
}
