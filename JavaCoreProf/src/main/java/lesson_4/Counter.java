package lesson_4;

public class Counter {
    volatile int c;

    public Counter( int c ) {
        this.c = c;
    }

    public void inc() {
        c++;
    }

    public void dec() {
        c--;
    }

    public int getC() {
        return c;
    }

    public void setC( int c ) {
        this.c = c;
    }
}

class MainCounter {
    public static void main( String[] args ) {
        Counter c = new Counter(0);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000000; i++) {
                    c.inc();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000000; i++) {
                    c.dec();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(c.getC());

    }
}