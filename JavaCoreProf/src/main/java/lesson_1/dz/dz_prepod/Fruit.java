package lesson_1.dz.dz_prepod;

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
