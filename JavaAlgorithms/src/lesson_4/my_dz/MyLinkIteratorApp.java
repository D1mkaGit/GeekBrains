package lesson_4.my_dz;

class MyLinkIterator {
    private MyLink<Object> current;
    private MyLink<Object> previous;
    private MyLinkedList<Object> list;

    public MyLinkIterator(MyLinkedList<Object> list) {
        this.list = list;
        this.reset();
    }

    public void reset() {
        current = list.getFirst();
    }

    public boolean atEnd() {
        return (current.getNext() == null);
    }

    public void nextLink() {
        previous = current;
        current = current.getNext();
    }

    public MyLink<Object> getCurrent() {
        return current;
    }

    public void insertAfter(Object item) {
        MyLink<Object> newLink = new MyLink<>(item);
        if (list.isEmpty()) {
            list.setFirst(newLink);
            current = newLink;
        } else {
            newLink.setNext(current.getNext());
            current.setNext(newLink);
            nextLink();
        }
    }

    public void insertBefore(Object item) {
        MyLink<Object> newLink = new MyLink<>(item);
        if (previous == null) {
            newLink.setNext(list.getFirst());
            list.setFirst(newLink);
            reset();
        } else {
            newLink.setNext(previous.getNext());
            previous.setNext(newLink);
            current = newLink;
        }
    }

    public Object deleteCurrent() {
        Object item = current.getItem();
        if (previous == null) {
            list.setFirst(current.getNext());
            reset();
        } else {
            previous.setNext(current.getNext());
            if (atEnd()) {
                reset();
            } else {
                current = current.getNext();
            }
        }
        return item;
    }
}

public class MyLinkIteratorApp {
    public static void main(String[] args) {
        MyLinkedList<Object> list = new MyLinkedList<>();
        MyLinkIterator itr = new MyLinkIterator(list);

        itr.insertAfter("Artem");
        itr.insertAfter("Борис");
        itr.insertBefore("Sergey");
        itr.insertAfter("Voland");

        System.out.println("Список: ");
        list.display();

        System.out.println("Текущй элемент: " + itr.getCurrent().getItem());
        System.out.println("Переводим текущий элемент на один назад");
        itr.nextLink();
        System.out.println("Текущй элемент: " + itr.getCurrent().getItem());

        System.out.println("Удаляем текущй элемент: " + itr.deleteCurrent());
        System.out.println("Список: ");
        list.display();

        System.out.println("НОВЫЙ ТЕСТ");
        System.out.println("Текущй элемент: " + itr.getCurrent().getItem());

        System.out.println("Листаем в конец списка");
        while (!itr.atEnd()){
            itr.nextLink();
        }
        System.out.println("Текущй элемент: " + itr.getCurrent().getItem());
        if (itr.atEnd())
            itr.reset();
        System.out.println("Переводим каретку на первый элемент");
        System.out.println("Текущй элемент: " + itr.getCurrent().getItem());
    }

}
