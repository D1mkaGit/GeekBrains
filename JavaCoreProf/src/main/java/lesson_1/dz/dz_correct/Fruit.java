package Lesson_1.DZ;

public abstract class Fruit {
    protected float weight;

    public Fruit(float weight) {
        this.weight = weight;
    }

    public abstract Fruit newInstance();

    public float getWeight() {
        return weight;
    }
}
