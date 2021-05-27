package mapsmaps;

import java.util.HashMap;
import java.util.Objects;
import java.util.TreeMap;

final class People implements Comparable<People>{
    String name;
    int age;

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        People people = (People) o;
        return age == people.age &&
                Objects.equals(name, people.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public int compareTo(People o) {
        return name.compareTo(o.name);
    }
}

public class JavaApplication7 {
    public static void main(String[] args){

        HashMap<People, String> map = new HashMap<>();
        map.put(new People("Artem", 10), "233f-a435-bc45-4352");
        map.put(new People("Viktor", 20), "184f-a835-bc45-4352");
        map.put(new People("Sergey", 14), "634f-a735-bc45-4352");
        map.put(new People("Ivan", 12), "234f-a435-bc45-4352");
        map.put(new People("Artem", 10), "233f-a435-bc45-4352");

        System.out.println(map.size());

        TreeMap<People, String> tree = new TreeMap<>(map);
    }
}

