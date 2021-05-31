package threads;

class SyncQueue {
    int n;
    boolean flag = false;
    synchronized int get() {
        while (!flag){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
        System.out.println("Объект "+n+" получен");
        flag = false;
        notify();
        return n;
    }

    synchronized void put(int n) {
        while (flag) {
            try {
                wait();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.n = n;
        System.out.println("Объект "+n+" доставлен");
        flag = true;
        notify();
    }
}
class Producer implements Runnable{
    SyncQueue q;

    public Producer(SyncQueue q){
        this.q = q;
        new Thread(this, "Отправитель").start();
    }

    @Override
    public void run() {
        int i = 0;
        while (true){
            q.put(i++);
        }
    }
}

class Consumer implements Runnable{
    SyncQueue q;

    public Consumer(SyncQueue q){
        this.q = q;
        new Thread(this, "Потребитель").start();
    }

    @Override
    public void run() {
        while (true){
            q.get();
        }
    }
}


