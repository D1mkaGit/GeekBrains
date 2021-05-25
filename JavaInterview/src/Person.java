public class Person {
    private String name;
    private int age;

    static {
        System.out.println("Выполнение статического блока инициализации Person");
    }

    {
        System.out.println("Выполнение блока инициализации Person");
    }


    public Person(Person p) {
        this(p.name, p.age);
    }

    public Person(String name, int age) {
        System.out.println("Выполнение конструктора Person");
        this.name = name;
        this.age = age;
    }
}

class Manager extends Person {
    static {
        System.out.println("Выполнение статического блока инициализации Manager");
    }

    {
        System.out.println("Выполнение блока инициализации Manager");
    }

    private String post;

    public Manager(String name, int age) {
        super(name, age);
        System.out.println("Конструктор менеджера");

    }
}

