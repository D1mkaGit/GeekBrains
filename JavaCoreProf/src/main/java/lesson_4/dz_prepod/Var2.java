package lesson_4.dz_prepod;

public class Var2 {
    static Object mon = new Object();
    static volatile char currentLetter = 'A';

    public static void main( String[] args ) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        synchronized (mon) {
                            while (currentLetter != 'A') {
                                mon.wait();
                            }
                            System.out.print("A");
                            currentLetter = 'B';
                            mon.notifyAll();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        synchronized (mon) {
                            while (currentLetter != 'B') {
                                mon.wait();
                            }
                            System.out.print("B");
                            currentLetter = 'C';
                            mon.notifyAll();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        synchronized (mon) {
                            while (currentLetter != 'C') {
                                mon.wait();
                            }
                            System.out.print("C");
                            currentLetter = 'A';
                            mon.notifyAll();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
