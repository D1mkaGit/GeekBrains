package lesson_4;

public class TestSyns {
    Object obj = new Object();

    public static synchronized void m1() {
        System.out.println("M1");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("M2");
    }

    public void m2() {
        System.out.println("M3");

        synchronized (obj) {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("M4");
    }
}

class StartSyns {
    public static void main( String[] args ) {
        TestSyns e1 = new TestSyns();
        TestSyns e2 = new TestSyns();

        new Thread(new Runnable() {
            @Override
            public void run() {
                e1.m1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                e2.m1();
            }
        }).start();
    }
}