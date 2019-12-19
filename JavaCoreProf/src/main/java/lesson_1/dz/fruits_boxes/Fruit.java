package lesson_1.dz.fruits_boxes;

public class Fruit {
    private float weight;

    public Fruit(float weight) {
        this.weight = weight;
    }

    public Fruit() {

    }


    public float getWeight() {
        return weight;
    }

    public Fruit[] setupArr(int count) {
        Fruit[] fruits = new Fruit[count];
        for (int i = 0; i < count; i++) {
            fruits[i] = new Fruit();
        }
        return fruits;
    }
}

class Apple extends Fruit {
    public Apple() {
        super(1.5f);
    }

    public Apple[] setupArr(int count) {
        Apple[] apples = new Apple[count];
        for (int i = 0; i < count; i++) {
            apples[i] = new Apple();
        }
        return apples;
    }
}

class Orange extends Fruit {
    public Orange() {
        super(1.0f);
    }

    public Orange[] setupArr(int count) {
        Orange[] oranges = new Orange[count];
        for (int i = 0; i < count; i++) {
            oranges[i] = new Orange();
        }
        return oranges;
    }
}
