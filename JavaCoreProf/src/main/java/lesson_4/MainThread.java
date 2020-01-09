package lesson_4;

public class MainThread {
    public static void main( String[] args ) {
//        Thread t1 = new Thread(new MyRunnableClass());
//        Thread t2 = new Thread(new MyRunnableClass());
//
//        t1.start();
//        t2.start();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i);
                }
            }
        });

        t1.start();

//        try {
//            t1.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        while (true) {
            if (!t1.isAlive()) {
                break;
            }
        }

        System.out.println("END");
    }
}

class MyRunnableClass implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }
}