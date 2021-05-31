package counter;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomInt {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10; j++) {
                        counter.increase();
                    }
                }
            }).start();
        }

        Thread.sleep(2000);
        System.out.println(counter.counter.get());
    }
}

class Counter {
    AtomicInteger counter;

    public Counter() {
        counter = new AtomicInteger();
    }

    void increase(){
        counter.incrementAndGet();
    }
}


