package lesson_1.dz.dz_correct;

public class Apple extends Fruit {
    public Apple() {
        super(1.0f);
    }

    @Override
    public Fruit newInstance() {
        return new Apple();
    }
}