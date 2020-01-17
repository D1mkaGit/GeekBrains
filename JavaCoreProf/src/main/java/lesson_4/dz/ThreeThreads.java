package lesson_4.dz;


//  1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз
//  (порядок – ABСABСABС). Используйте wait/notify/notifyAll.

public class ThreeThreads {
    private static final int REPEAT_COUNT = 5;
    private final Object mon = new Object();
    private volatile char currentLetter = 'A';

    public static void main( String[] args ) {
        ThreeThreads w = new ThreeThreads();
        Thread t1 = new Thread(() -> w.printLetterSynced('A', 'B'));
        Thread t2 = new Thread(() -> w.printLetterSynced('B', 'C'));
        Thread t3 = new Thread(() -> w.printLetterSynced('C', 'A'));
        t1.start();
        t2.start();
        t3.start();
    }

    private void printLetterSynced( char _currentLetter, char nextLetter ) {
        synchronized (mon) {
            try {
                for (int i = 0; i < ThreeThreads.REPEAT_COUNT; i++) {
                    while (currentLetter != _currentLetter) {
                        mon.wait();
                    }
                    System.out.print(_currentLetter);
                    currentLetter = nextLetter;
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
