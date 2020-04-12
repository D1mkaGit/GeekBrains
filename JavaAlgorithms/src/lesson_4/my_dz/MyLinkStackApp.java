package lesson_4.my_dz;

class Link<T> {
    private T item;
    private Link<T> next;

    public Link(T item) {
        this.item = item;
    }

    public void display() {
        System.out.println(this.item);
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public Link<T> getNext() {
        return next;
    }

    public void setNext(Link<T> next) {
        this.next = next;
    }

}

class LinkedList {
    private Link<Object> first;

    public LinkedList() {
        this.first = null;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void insert(Object item) {
        Link<Object> newLink = new Link<>(item);
        if (!isEmpty()) {
            newLink.setNext(first);
        }
        first = newLink;
    }

    public Link<Object> delete() {
        if(!isEmpty()) {
            Link<Object> temp = first;
            first = first.getNext();
            return temp;
        }
        return null;
    }

    public void display() {
        if(!isEmpty()) {
            Link<Object> current = first;
            while (current != null) {
                current.display();
                current = current.getNext();
            }
        }else{
            System.out.println("список пуст");
        }
    }
}

class StackList {
    private final LinkedList list;

    public StackList() {
        this.list = new LinkedList();
    }

    public void push(Object item) {
        list.insert(item);
    }

    public Object pop() {
        return list.delete().getItem();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void display() {
        list.display();
    }
}

public class MyLinkStackApp {
    public static void main(String[] args) {
        StackList sl = new StackList();
        String[] stringsList = {"Artem", "Boris", "David", "Edvard"};
        for (String el : stringsList) {
            sl.push(el);
            System.out.println("Элемент " + el + " добавлен в стек");
        }
        while (!sl.isEmpty()) {
            System.out.println("Элемент " + sl.pop() + " удален из стека");
        }
    }
}
