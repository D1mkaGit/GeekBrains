package lesson_4.my_dz;

class Queue {
    private MyLinkedList<Object> queue;

    public Queue() {
        this.queue = new MyLinkedList<>();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void insert(Object item) {
        queue.insertToEnd(item);
    }

    public Object delete() {
        return queue.deleteFirst();
    }

    public void display() {
        queue.display();
    }
}

public class MyLinkQueueApp {
    public static void main(String[] args) {
        Queue q = new Queue();
        String[] sList = {"Andrew", "Boris", "David", "Elena", "Gorge"};
        for (String item : sList) {
            q.insert(item);
            System.out.println("Элемент " + item + " добавлен в очередь");
        }
        while (!q.isEmpty()) {
            System.out.println("Элемент " + q.delete() + " удален из стека");
        }
    }
}
