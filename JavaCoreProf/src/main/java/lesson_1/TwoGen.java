package lesson_1;

public class TwoGen<T, V> {

    private T obj1;
    private V obj2;

    public TwoGen() {
    }

    public T getObj1() {
        return obj1;
    }

    public void setObj1(T obj1) {
        this.obj1 = obj1;
    }

    public V getObj2() {
        return obj2;
    }

    public void setObj2(V obj2) {
        this.obj2 = obj2;
    }

    public void showTypes() {
        System.out.println("Тип T " + obj1.getClass().getName());
        System.out.println("Тип V " + obj2.getClass().getName());
    }

    public void parse() {
        System.out.println("Старт разбора");
    }
}
