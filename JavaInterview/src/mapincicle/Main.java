package mapincicle;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Integer, People> map = new HashMap<>();

        map.put(1, new People("Artem", 10));
        map.put(2, new People("Artem", 12));
        map.put(3, new People("Artem", 15));

        for (Map.Entry<Integer, People> entry : map.entrySet()) {
            Integer key = entry.getKey();
            People value = entry.getValue();
            System.out.println("Ключ: " + key + " Значение: " + value);

        }
    }
}


class People implements Comparable<People> {
    String name;
    int age;

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(People o) {
        return name.compareTo(o.name);
    }
}