package lesson_3.dz;

import java.util.EmptyStackException;

public class MyDeque<Item> {
    private Item[] list;
    private int size = 0;
    private final int DEFAULT_CAPACITY = 10;

    private int head = 0;
    private int tail = 0;

    public MyDeque( int capacity ) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("bad size");
        }
        list = (Item[]) new Object[capacity];
    }

    public MyDeque() {
        list = (Item[]) new Object[DEFAULT_CAPACITY];
    }

    // методы вставки
    public void insertLeft( Item item ) {
        if (isFull()) {
            throw new StackOverflowError();
        }
        list[head = (head - 1) & (list.length - 1)] = item;
        size++;
    }

    public void insertRight( Item item ) {
        if (isFull()) {
            throw new StackOverflowError();
        }
        list[tail] = item;
        size++;
        tail = nextIndex(tail);
    }

    // методы удаления
    public Item removeLeft() {
        Item value = peekLeft();
        size--;
        list[head] = null;
        head = nextIndex(head);
        return value;
    }

    public Item removeRight() {
        Item value = peekRight();
        size--;
        list[tail] = null;
        tail = prevIndex(tail); // тут нужно что-то сделать, чтобы в конце в ноль не возвращалось значение
        return value;
    }

    private int nextIndex( int index ) {
        return (index + 1) % list.length;
    }

    private int prevIndex( int index ) {
        return (index - 1) % list.length;
    }

    private Item peekLeft() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list[head];
    }

    private Item peekRight() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list[tail];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == list.length;
    }

    public int size() {
        return size;
    }
}
