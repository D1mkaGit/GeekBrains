package lesson_3;

import java.util.EmptyStackException;

public class MyStack<Item> {
    private Item[] list;
    private int size;
    private final int DEFAULT_CAPACITY = 10;
    private int currentCapacity;

    public MyStack( int capacity ) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("bad size");
        }
        list = (Item[]) new Object[capacity];
        currentCapacity = capacity;
    }

    public MyStack() {
        list = (Item[]) new Object[DEFAULT_CAPACITY];
        currentCapacity = DEFAULT_CAPACITY;
    }

    // метод вставки
    public void push( Item item ) {
        if (isFull()) {
            throw new StackOverflowError();
        }
        list[size] = item;
        size++;
    }

    public int getSize() {
        return size;
    }

    public void EnlargeStack() {
        EnlargeStack(DEFAULT_CAPACITY);
    }

    public void EnlargeStack( int capacity ) {
        reCapacity(currentCapacity + capacity);
    }

    // метод удаления
    public Item pop() {
        Item temp = peek();
        size--;
        list[size] = null;
        return temp;
    }

    private Item peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == list.length;
    }

    private void reCapacity( int newCapacity ) {
        Item[] tempArr = (Item[]) new Object[newCapacity];
        System.arraycopy(list, 0, tempArr, 0, size);
        list = tempArr;
    }
}
