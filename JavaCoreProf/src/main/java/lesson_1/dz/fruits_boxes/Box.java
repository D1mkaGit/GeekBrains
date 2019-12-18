package lesson_1.dz.fruits_boxes;

import java.util.Arrays;

public class Box<T extends Fruit> {
    T[] inBox;

    public Box(T[] inBox) {
        this.inBox = inBox;
    }

    public float getWeight() {
        if (inBox == null || inBox[0] == null) return 0.0f;
        else return (inBox.length) * inBox[0].getWeight();
    }

    public boolean compare(T[] _inBox) {
        return getWeight() == (_inBox.length) * _inBox[0].getWeight();
    }

    public void copyContentFrom(T[] _inBox) {
        int inBoxLength = 0;
        if (inBox != null) inBoxLength = inBox.length;
        T[] result = Arrays.copyOf(inBox, inBoxLength + _inBox.length);
        System.arraycopy(_inBox, 0, result, inBoxLength, _inBox.length);
        inBox = result;
    }

    public void addFruit(T newFruit) {
        int inBoxLength;
        if (inBox != null && inBox[0] != null) {
            inBoxLength = inBox.length;
            T[] result = Arrays.copyOf(inBox, inBoxLength + 1);
            System.arraycopy(newFruit, 0, result, inBoxLength, 1);
            inBox = result;
        } else {
            this.inBox = (T[]) newFruit.setupArr(1);
        }
    }

    public void emptyContentInArr() {
        for (int i = 0; i < inBox.length; i++) {
            inBox[i] = null;
        }
    }
}
