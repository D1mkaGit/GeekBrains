package singleton;

public class Main {
    public static void main(String[] args) {
        Director dir = SingleDirector.getInstance();
    }
}

class Director {

    String name;
    int age;

    public Director() {

    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}


class SingleDirector {
    private static Director instance;

    private SingleDirector() { }

    public static Director getInstance() {
        if (instance == null) {
            synchronized (Director.class) {
                if (instance == null) {
                    instance = new Director();
                }
            }
        }

        return instance;
    }
}



