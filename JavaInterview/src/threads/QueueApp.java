package threads;

public class QueueApp {
    public static void main(String[] args) {
        SyncQueue q = new SyncQueue();
        new Producer(q);
        new Consumer(q);
    }
}
