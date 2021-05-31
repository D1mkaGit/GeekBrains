package dz3.counter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

class Counter implements Runnable {
    private final ReentrantLock lock;
    private static int counter = 0;
    private static final int limit = 5000;
    private static final int threadPoolSize = 5;

    public Counter(ReentrantLock lock) {
        this.lock = lock;
    }

    public static void main(String[] args) {
        ReentrantLock sharedLock = new ReentrantLock();
        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);
        for (int i = 0; i < threadPoolSize; i++) {
            executorService.submit(new Counter(sharedLock));
        }
        executorService.shutdown();
    }

    @Override
    public void run() {
        while (counter < limit) {
            increaseCounter();
        }
    }

    private void increaseCounter() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " : " + counter);
            counter++;
        } finally {
            lock.unlock();
        }
    }
}



