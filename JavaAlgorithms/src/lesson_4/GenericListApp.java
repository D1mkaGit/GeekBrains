package lesson_4;

public class GenericListApp {

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();

        list.insert("Artem");
        list.insert("Roman");

        System.out.println(list.find("Artem"));

        LinkedList<People> peopleList = new LinkedList<>();
        peopleList.insert(new People("Artem", 22));
        peopleList.insert(new People("Roman", 18));

        System.out.println(peopleList.find(new People("Artem", 22)).toString());
    }
}