package lesson_4.my_dz;

public class MyLinkedList<Object> {
    private MyLink<Object> first;
    private MyLink<Object> last;

    public MyLinkedList() {
        this.first = null;
        this.last = null;
    }

    public MyLink<Object> getFirst() {
        return first;
    }

    public void setFirst(MyLink<Object> first) {
        this.first = first;
    }

    public boolean isEmpty() {
        return (this.first == null);
    }

    public void insert(Object item) {
        MyLink<Object> newLink = new MyLink<>(item);
        newLink.setNext(first);
        first = newLink;
    }

    public void insertToEnd(Object item) {
        MyLink<Object> newLink = new MyLink<>(item);
        if (isEmpty()) {
            first = newLink;
            last = first;
        } else {
            last.setNext(newLink);
            last = newLink;
        }
    }

    public void display() {
        MyLink<Object> current = first;
        while (current != null) {
            current.display();
            current = current.getNext();
        }
    }

    public Object deleteFirst(){
        MyLink<Object> temp = first;
        if(first.getNext()==null)
            last=null;
        first=first.getNext();
        return temp.getItem();
    }

    public Object find(Object searchItem) {
        MyLink<Object> search = new MyLink<>(searchItem);
        MyLink<Object> current = first;
        while (current != null) {
            if (current.getItem().equals(search.getItem())) {
                return search.getItem();
            }
            current = current.getNext();
        }
        return null;
    }

    public Object delete(Object itemToDelete) {
        MyLink<Object> current = first;
        MyLink<Object> previous = first;
        while (!current.getItem().equals(itemToDelete))
            if (current.getNext() == null) {
                return null;
            } else {
                previous = current;
                current = current.getNext();
            }
        if (current == first) {
            first = first.getNext();
        } else {
            previous.setNext(current.getNext());
        }
        return current.getItem();
    }
}
