package lesson_7;

import lesson_7.Anon.MyAnon;

public class Cat {
    public String color;
    String name;
    private int age;

    public Cat( String name, String color, int age ) {
        this.name = name;
        this.color = color;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor( String color ) {
        this.color = color;
    }

//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }

    @MyAnon
    public void info() {
        System.out.println(name + " " + color + " " + age);
    }

    @MyAnon(priotity = 2)
    public void info2() {
        System.out.println(name + " " + color + " " + age + " info2");
    }
}
