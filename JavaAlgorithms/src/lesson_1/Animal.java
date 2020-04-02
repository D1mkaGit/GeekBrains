package lesson_1;

public class Animal {
    void voice() {
        System.out.println("voice Animal");
    }
}

class Cat extends Animal {
    void voice() {
        System.out.println("voice Cat");
    }
}

class SuperCat extends Cat {
    void voice() {
        System.out.println("voice SuperCat");
    }
}

class MainP {
    public static void main(String[] args) {
        Animal[] animals = {new Animal(), new Cat(), new SuperCat()};

        for (Animal a: animals) {
            a.voice();
        }
    }
}