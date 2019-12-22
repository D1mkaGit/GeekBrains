package lesson_3;

import java.io.Serializable;

public class Book implements Serializable {
    String title;

    public Book(String title) {
        this.title = title;
    }
}
