package comparator;

import java.util.*;

public class Main {
    public static void main(String[] args){
        List<People> list = new ArrayList<>();
        list.add(new People("Vova", 10));
        list.add(new People("Artem", 20));
        list.add(new People("Kirill", 10));
        Collections.sort(list);
        System.out.println(list);
    }

}

class People implements Comparable<People>{
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

    Set<Integer> set = new TreeSet<>(Arrays.asList(new Integer[]{null,2,3,4})); // TreeSet cannot take
    // null

    Set<Integer> set1 = new HashSet<>(Arrays.asList(new Integer[]{1,null,3,4}));

    Set<Integer> set2 = new LinkedHashSet<>(Arrays.asList(new Integer[]{null,null,3,4}));
}
