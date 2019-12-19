package lesson_1.dz.dz_correct;

public class Orange extends Fruit {
    public Orange() {
        super(1.5f);
    }

    @Override
    public Fruit newInstance() {
        return new Orange();
    }
}