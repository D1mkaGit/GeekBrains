package Delete;

public class MyLinkedStack<T> {
    private MyLinkedList<T> stack = new MyLinkedList<>();

    public void push(T value){
        stack.insertFirst(value);
    }
    public T pop(){
        return stack.removeFirst();
    }
    public T peek(){
        return stack.getFirst();
    }

    public int size(){
        return stack.size();
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }
}
