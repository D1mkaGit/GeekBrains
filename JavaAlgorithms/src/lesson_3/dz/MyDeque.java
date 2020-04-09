package lesson_3.dz;

import java.util.NoSuchElementException;

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
        list[head - 1] = null;
        return value;
    }

    public Item removeRight() {
        Item value = peekRight();
        size--;
        list[tail] = null;
        return value;
    }

    public Item getFirst() {
        return peekLeft();
    }

    public Item getLast() {
        return peekRight();
    }

    private int nextIndex( int index ) {
        return (index + 1);
    }

    private int prevIndex( int index ) {
        return (index - 1);
    }

    private Item peekLeft() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item result = list[head];
        if (result == null)
            return null;
        head = nextIndex(head);
        return result;
    }

    private Item peekRight() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        tail = prevIndex(tail);
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
