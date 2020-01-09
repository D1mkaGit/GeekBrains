package lesson_4;

public class TestParallel {
    private static Integer n = new Integer(300);

    public static void main( String[] args ) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (n) {
                    System.out.println("x");
                    n++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("y");
                }
            }
        }).start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (n) {
                    System.out.println("x");
                    n++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("y");
                }
            }
        }).start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (n) {
                    System.out.println("x");
                    n++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("y");
                }
            }
        }).start();

    }
}
