package Lesson_5.Optional;

import java.util.Optional;

public class Rep {

    public static Optional<Person> findById( int i ) {
        Person p = new Person();
        p.setAge(10);
        Optional<Person> per = Optional.of(p);
        return per;
    }
}
