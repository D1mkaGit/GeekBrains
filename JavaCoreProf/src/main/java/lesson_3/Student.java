package lesson_3;

import java.io.Serializable;

public class Student extends Human implements Serializable {
    int id;
    String name;

    Book book;

    public Student(int id, String name) {
        System.out.println("Student run");
        this.id = id;
        this.name = name;
    }

    public void info() {
        System.out.println(id + " " + name);
    }
}
