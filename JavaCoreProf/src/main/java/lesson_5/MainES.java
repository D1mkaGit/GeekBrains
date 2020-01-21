package lesson_5;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

public class MainES {
    public static void main( String[] args ) throws ExecutionException, InterruptedException {
//        ExecutorService pool = Executors.newFixedThreadPool(4, new ThreadFactory() {
//            @Override
//            public Thread newThread(Runnable r) {
//                System.out.println("new thread!");
//                return new Thread(r);
//            }
//        });
//
//        pool.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//                System.out.println(1);
//            }
//        });
//
//        pool.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//                System.out.println(2);
//            }
//        });
//
//        pool.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//                System.out.println(3);
//            }
//        });
//
//        pool.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//                System.out.println(4);
//            }
//        });
//
//        pool.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//                System.out.println(5);
//            }
//        });
//
//        pool.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//                System.out.println(6);
//            }
//        });
//
//        pool.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//                System.out.println(7);
//            }
//        });
//
//        pool.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//                System.out.println(8);
//            }
//        });
//
//        try {
//            pool.awaitTermination(1000, TimeUnit.MILLISECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        pool.shutdown();


//////////////////////////////////

//        ExecutorService service = Executors.newSingleThreadExecutor();
//        int[] source = {1,2,3,4};
//        int[] res1 = new int[2];
//        int[] res2 = new int[2];
//
//        System.arraycopy(source, 0, res1, 0, res1.length);
//        System.arraycopy(source, 2, res2, 0, res1.length);
//
//        System.out.println(Arrays.toString(res1));
//        System.out.println(Arrays.toString(res2));
//
//        Callable<Integer> callable1 = new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                int res = 0;
//                for (int i = 0; i < res1.length; i++) {
//                    res += res1[i];
//                }
//                return res;
//            }
//        };
//
//        Callable<Integer> callable2 = new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                int res = 0;
//                for (int i = 0; i < res2.length; i++) {
//                    res += res2[i];
//                }
//                return res;
//            }
//        };
//
//        Future<Integer> f1 = service.submit(callable1);
//        Future<Integer> f2 = service.submit(callable2);
//
//        System.out.println(f1.get() + f2.get());
//        service.shutdown();


//////////////////////////////////

        Random random = new Random();

//        ScheduledExecutorService execService = Executors.newScheduledThreadPool(2);
//        execService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(new Date() + "start work");
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(new Date() + "end work");
//            }
//        },0, 1000L, TimeUnit.MILLISECONDS);


//        ScheduledExecutorService execService = Executors.newScheduledThreadPool(2);
//        execService.scheduleWithFixedDelay(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(new Date() + "start work");
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(new Date() + "end work");
//            }
//        },0, 1000L, TimeUnit.MILLISECONDS);

        // testScahecule();


//        ArrayBlockingQueue<String> abq = new ArrayBlockingQueue<>(10);
//        abq.put("asd");


//        ConcurrentHashMap<Integer, String> chm = new ConcurrentHashMap<>();
//
//        Map<Integer, String> hm = Collections.synchronizedMap(new HashMap<>());


//        Semaphore smp = new Semaphore(4);
//
//        ExecutorService serv = Executors.newFixedThreadPool(10);
//        for (int i = 0; i < 10; i++) {
//            serv.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        smp.acquire();
//                        System.out.println(1);
//                        Thread.sleep(2000);
//                        smp.release();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                   // System.out.println(2);
//                }
//            });
//        }
//
//        serv.shutdown();


//        CountDownLatch cdl = new CountDownLatch(10);
//
//        for (int i = 0; i < 10; i++) {
//            int w = i;
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(w + "-1");
//                    try {
//                        Thread.sleep((int) (Math.random() * 500));
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(w + "-2");
//                    cdl.countDown();
//                }
//            }).start();
//        }
//        cdl.await();
//
//        System.out.println("END");

//
//        CyclicBarrier cb = new CyclicBarrier(10);
//
//        for (int i = 1; i < 11; i++) {
//            int w = i;
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(w + " start");
//                    try {
//                        Thread.sleep((int) (Math.random() * 5000));
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(w + " ready");
//                    try {
//                        cb.await();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } catch (BrokenBarrierException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(w + " end");
//                }
//            }).start();
//        }

//        ReentrantLock r1 = new ReentrantLock();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                r1.lock();
//                System.out.println(1);
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(2);
//                r1.unlock();
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                r1.lock();
//                System.out.println(3);
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(4);
//                r1.unlock();
//            }
//        }).start();


        AtomicInteger ai = new AtomicInteger(100);

        //  System.out.println(ai.incrementAndGet());

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (ai.incrementAndGet() == 101) {
                    System.out.println(Thread.currentThread().getName() + " win");
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                if (ai.incrementAndGet() == 101) {
                    System.out.println(Thread.currentThread().getName() + " win");
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                if (ai.incrementAndGet() == 101) {
                    System.out.println(Thread.currentThread().getName() + " win");
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();

//        t1.join();
//        t2.join();
//
//        System.out.println(ai);

    }

    static void testScahecule() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 51);
        calendar.set(Calendar.SECOND, 0);
        Date time = calendar.getTime();
        Timer timer = new Timer();
        timer.schedule(new RemindTask(), time);
    }
}

class RemindTask extends TimerTask {

    @Override
    public void run() {
        System.out.println("Time up!");
    }
}
