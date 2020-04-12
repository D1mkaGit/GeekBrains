package lesson_4.my_dz;

public class MyTwoSideLinkList<Object> {
    private MyLink<Object> first;
    private MyLink<Object> last;

    public MyTwoSideLinkList() {
        first = null;
        last = null;
    }

    public MyLink<Object> getFirst() {
        return first;
    }

    public void setFirst(MyLink<Object> first) {
        this.first = first;
    }

    public MyLink<Object> getLast() {
        return last;
    }

    public void setLast(MyLink<Object> last) {
        this.last = last;
    }

    public boolean isEmpty() {
        return (this.first == null);
    }

    public void insertFirst(Object item) {
        MyLink<Object> newLink = new MyLink<>(item);
        if (this.isEmpty())
            last = newLink;
        newLink.setNext(first);
        first = newLink;
    }

    public void insertLast(Object item) {
        MyLink<Object> newLink = new MyLink<>(item);
        if (this.isEmpty()) {
            first = newLink;
        } else {
            last.setNext(newLink);
        }
    }

    public MyLink<Object> deleteFirst() {
        MyLink<Object> temp = first;
        if (first.getNext() == null) {
            last = null;
        }
        first = first.getNext();
        return temp;
    }

    public MyLink<Object> deleteLast() {
        MyLink<Object> tempFirst = first;
                while (tempFirst.getNext().getNext()!=null) {
            tempFirst = tempFirst.getNext();
        }
        MyLink<Object> tempLast = tempFirst.getNext();
        last = tempFirst;
        last.setNext(null);
        return tempLast;
    }

    public void display() {
        MyLink<Object> current = first;
        while (current != null) {
            current.display();
            current = current.getNext();
        }
    }

    public MyLink<Object> find(Object itemToFind) {
        MyLink<Object> current = first;
        while (!current.getItem().equals(itemToFind)) {
            if (current.getNext() == null) {
                return null;
            } else {
                current = current.getNext();
            }
        }
        return current;
    }

    public MyLink<Object> delete(Object itemToDelete) {
        MyLink<Object> current = first;
        MyLink<Object> previous = first;
        while (!current.getItem().equals(itemToDelete)) {
            if (current.getNext() == null) {
                return null;
            } else {
                previous = current;
                current = current.getNext();
            }
        }
        if (current.equals(first)) {
            first = first.getNext();
        } else {
            previous.setNext(current.getNext());
        }
        return current;
    }
}
