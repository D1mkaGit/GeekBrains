package lesson_5;

public class ThreadDemo extends Thread {
    public static void main( String[] args ) throws InterruptedException {
        ThreadDemo t1 = new ThreadDemo();
        ThreadDemo t2 = new ThreadDemo();

        t1.setPriority(8);
        t2.setPriority(1);

        t1.start();
        Thread.sleep(1000);
        t2.start();
    }

    @Override
    public void run() {
        System.out.println("test thread " + getName());
    }
}