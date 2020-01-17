package lesson_4.dz;

import static lesson_4.dz.MultiFunctionalMachine.jobType.*;

// 2. Создать модель MFU с возможностью печати и сканирования(копирования)
// (данные процессы могут происходить параллельно).

public class MultiFunctionalMachine {
    private final Object printLock = new Object();
    private final Object scanLock = new Object();
    private volatile boolean isPrint;
    private volatile boolean isScan;

    public static void main( String[] args ) {
        MultiFunctionalMachine mfu = new MultiFunctionalMachine();
        Thread t1 = new Thread(() -> mfu.doPrint("A"));
        Thread t2 = new Thread(() -> mfu.doScan("B", false)); // скан не ждет принта
        Thread t3 = new Thread(() -> mfu.doPrint("C"));
        Thread t4 = new Thread(() -> mfu.doScan("D", true)); // копия ждет принта и другого скана
        Thread t5 = new Thread(() -> mfu.doScan("E", false));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

    /**
     * @param executorName - имя потока (сотрудника, который пользуется МФУ)
     */
    private void doPrint( String executorName ) {
        synchronized (printLock) {
            try {
                while (isPrint) {
                    printLock.wait();
                }
                doJob(PRINT, 5000, executorName);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param executorName - имя потока (сотрудника, который пользуется МФУ)
     * @param copy         - boolean, если копирует, то МФУ будет ждать принта и другого скана
     */
    private void doScan( String executorName, boolean copy ) {
        synchronized (scanLock) {
            try {
                if (copy) {
                    synchronized (printLock) {
                        while (isPrint) {
                            printLock.wait();
                        }
                        while (isScan) {
                            scanLock.wait();
                        }
                        doJob(COPY, 3000, executorName);
                    }
                } else {
                    while (isScan) {
                        scanLock.wait();
                    }
                    doJob(SCAN, 2000, executorName);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param jt           - тип работы,  enum jobType
     * @param duration     - время ожидания в милисекундах
     * @param executorName - имя потока (сотрудника, который пользуется МФУ)
     */
    private void doJob( jobType jt, int duration, String executorName ) throws InterruptedException {
        System.out.println("Start " + jt + " " + executorName);
        if (jt == PRINT) isPrint = true;
        else isScan = true;
        Thread.sleep(duration);
        System.out.println("End of " + jt + " " + executorName);
        if (jt == PRINT) {
            isPrint = false;
            printLock.notifyAll();
        } else {
            isScan = false;
            scanLock.notifyAll();
        }
    }

    enum jobType {
        PRINT,
        COPY,
        SCAN
    }
}
