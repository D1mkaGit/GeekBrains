package lesson_5.optional;

import java.util.Optional;

public class Person {

    private Optional<String> firstName;

    private Optional<String> secondName;

    private Optional<Integer> age;

    public Optional<String> getFirstName() {
        return firstName;
    }

    public void setFirstName( String firstName ) {
        this.firstName = Optional.ofNullable(firstName);
    }

    public Optional<String> getSecondName() {
        return secondName;
    }

    public void setSecondName( String secondName ) {
        this.secondName = Optional.of(secondName);
    }

    public Optional<Integer> getAge() {
        return age;
    }

    public void setAge( Integer age ) {
        this.age = Optional.ofNullable(age);
    }
}
