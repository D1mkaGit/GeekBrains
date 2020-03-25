package Lesson_1;

public class Person {
    int a;
    String name;

    public Person(String name) {
        this.name = name;
    }

    void test(int c) {

    }

    @Override
    public boolean equals(Object obj) {
//        if (obj == this) {
//            return true;
//        }
        if (!(obj instanceof Person)) {
            return false;
        }


        Person p = (Person) obj;
        return p.name == this.name;
    }

    Number test() {
        return 1;
    }
}

class Human extends Person {

    public Human(String name) {
        super(name);
    }

    @Override
    Double test() {
        return 1.1;
    }
}
