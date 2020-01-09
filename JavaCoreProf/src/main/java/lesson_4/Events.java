package lesson_4;

public class Events {

    public static void main( String[] args ) {
        Object стул1 = new Object();
        Object стул2 = new Object();

        Thread чел1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Чел1 подошел к стулу 1");
                synchronized (стул1) {
                    System.out.println("Чел1 сел на стул 1");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Чел1 встал со стула 1");
                }
            }
        });
        чел1.start();

        Thread чел2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Чел2 подошел к стулу 1");
                synchronized (стул1) {
                    System.out.println("Чел2 сел на стул 1");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Чел2 встал со стула 1");
                }
            }
        });
        чел2.start();

        Thread чел3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Чел3 подошел к стулу 2");
                synchronized (стул2) {
                    System.out.println("Чел3 сел на стул 2");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Чел3 встал со стула 2");
                }
            }
        });
        чел3.start();
    }
}
