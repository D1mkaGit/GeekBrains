package equalsandhashcode;

import java.util.Objects;

public class Main{
    public static void main(String[] args) {
        Employee em1 = new Employee("Artem", 31);
        Employee em2 = new Employee("Artem", 31);

        System.out.println(em1.equals(em2));

        System.out.println(em1.hashCode()==em2.hashCode());
    }
}

class Employee{
    String name;
    int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age &&
                Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}


