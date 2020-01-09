package lesson_4;

public class TestDeadLock {
    static Object lock1 = new Object();
    static Object lock2 = new Object();

    public static void main( String[] args ) {
        DeadThreadLookOne deadThreadLookOne = new DeadThreadLookOne();
        deadThreadLookOne.start();
        DeadThreadLookTwo deadThreadLookTwo = new DeadThreadLookTwo();
        deadThreadLookTwo.start();
    }

    static class DeadThreadLookOne extends Thread {
        @Override
        public void run() {
            synchronized (lock1) {
                System.out.println("DeadThreadLookOne держит lock1...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("DeadThreadLookOne ждет look2...");
                synchronized (lock2) {
                    System.out.println("DeadThreadLookOne держит lock1 и look2");
                }
            }
        }
    }

    static class DeadThreadLookTwo extends Thread {
        @Override
        public void run() {
            synchronized (lock2) {
                System.out.println("DeadThreadLookTwo держит lock2...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("DeadThreadLookTwo ждет look1...");
                synchronized (lock1) {
                    System.out.println("DeadThreadLookTwo держит lock2 и look1");
                }
            }
        }
    }

}
