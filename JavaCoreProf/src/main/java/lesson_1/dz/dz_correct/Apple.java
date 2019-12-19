package Lesson_1.DZ;

public class Apple extends Fruit {
    public Apple() {
        super(1.0f);
    }

    @Override
    public Fruit newInstance() {
        return new Apple();
    }
}