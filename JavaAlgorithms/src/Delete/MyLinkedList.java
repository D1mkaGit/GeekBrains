package Delete;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MyLinkedList<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size = 0;

    public MyLinkedList() {
        first = null;
        last = null;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iter();
    }

    private class Iter implements Iterator<Item>{
        Node current = new Node(null,first);
        @Override
        public boolean hasNext() {
            return current.getNext() != null;
        }

        @Override
        public Item next() {
            current = current.next;
            return (Item) current.getValue();
        }
    }

    class Node<Item> {
        private Item value;
        private Node next;
        private Node previous;

        public Node(Item value) {
            this.value = value;
        }

        public Node(Item value, Node next) {
            this.value = value;
            this.next = next;
        }

        public Item getValue() {
            return value;
        }

        public void setValue(Item value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }


    public void insertFirst(Item item) {
        Node newNode = new Node(item);
        newNode.setNext(first);
        if (isEmpty()) {
            last = newNode;
        } else {
            first.setPrevious(newNode);
        }
        first = newNode;
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node oldFirst = first;
        first = first.next;
        size--;
        if (isEmpty()) {
            last = null;
        } else {
            first.setPrevious(null);
        }
        return (Item) oldFirst.getValue();
    }

    public void insertLast(Item item) {
        Node newNode = new Node(item);
        if (isEmpty()) {
            first = newNode;
        } else {
            newNode.setPrevious(last);
            last.setNext(newNode);
        }
        last = newNode;
        size++;
    }

    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node oldLast = last;
        if (last.getPrevious() != null) {
            last.getPrevious().setNext(null);
        } else {
            first = null;
        }
        size--;
        last = last.getPrevious();
        return (Item) oldLast.getValue();
    }

    public void insert(Item item, int index) {
        if (index <= 0) {
            insertFirst(item);
            return;
        }
        if (index > size) {
            index = size;
        }
        Node current = first;
        int i = 0;
        while (i < index - 1) {
            current = current.next;
            i++;
        }
        Node newNode = new Node(item);
        newNode.setNext(current.next);
        newNode.setPrevious(current);
        current.setNext(newNode);
        newNode.getNext().setPrevious(newNode);
        size++;
    }

    public boolean remove(Item item) {
        if (isEmpty()) {
            return false;
        }
        if (first.getValue().equals(item)) {
            removeFirst();
            return true;
        }
        Node current = first;
        while (current != null &&
                !current.getValue().equals(item)) {
            current = current.next;
        }
        if (current == null) {
            return false;
        }
        if(current == last){
            removeLast();
            return true;
        }
        current.getNext().setPrevious(current.previous);
        current.getPrevious().setNext(current.next);
        size--;
        return true;
    }

    public boolean contains(Item item) {
        return indexOf(item) > -1;
    }

    public int indexOf(Item item) {
        Node current = first;
        int index = 0;
        while (current != null) {
            if (current.getValue().equals(item)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    public Item getFirst() {
        if (isEmpty()) {
            return null;
        }
        return (Item) first.value;
    }

    public Item getLast() {
        if (isEmpty()) {
            return null;
        }
        return (Item) last.value;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        Node current = first;
        StringBuilder sb = new StringBuilder();
        while (current != null) {
            sb.append(current.getValue() + ", ");
            current = current.next;
        }
        return sb.toString();
    }
}
