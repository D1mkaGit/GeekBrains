package Lesson_1;

import java.util.Arrays;

public class Animal {

    private int mass[] = {1,2,3};

    public int[] getMass() {
        return mass;
    }

    String name;

    public Animal(String name) {
        //System.out.println("Animal");
        this.name = name;
    }

    void info() {
        System.out.println(Arrays.toString(mass));
    }
}

class Cat extends Animal {
    String color;

    public Cat(String name, String color) {
        super(name);
        //System.out.println("Cat");
        this.color = color;
    }

    void info() {
        System.out.println("Cat");
    }
}

class SuperCat extends Cat {
    String surName;

    public SuperCat(String name, String color, String surName) {
        super(name, color);
       // System.out.println("SuperCat");
        this.surName = surName;
    }

    void info() {
        System.out.println("SuperCat");
    }

//    void test() {
//        super.surName = "sad";
//        super.color
//    }
}

class MainAnimals {
    public static void main(String[] args) {
        //SuperCat superCat = new SuperCat("name1", "red", "name2");
        Animal animal = new Animal("asd");
        animal.info();
        int[] mass = animal.getMass();
        mass[0] = 10;
        animal.info();


//        Animal[] mass = new Animal[]{new Animal("asd"), new Cat("asd", "asd"), new SuperCat("asdsa", "asd", "asd")};
//
//        for (Animal o:mass) {
//            o.info();
//        }
    }
}

abstract class SuperBox {

}