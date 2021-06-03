package dz3.pingpong;

public class PingPong {

    static String str = "Ping";
    final int interval = 1000;

    synchronized void ping() throws InterruptedException {
        while (str.equals("Pong")){
            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println(str);
        str = "Pong";
        Thread.sleep(interval);
        notify();
    }

    synchronized void pong() throws InterruptedException {
        while (str.equals("Ping")){
            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println(str);
        str = "Ping";
        Thread.sleep(interval);
        notify();
    }
}

class Ping implements Runnable{

    final PingPong pp;

    public Ping(PingPong pp) {
        this.pp = pp;
    }

    @Override
    public void run() {
        while (true){
            try {
                pp.ping();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class Pong implements Runnable{

    final PingPong pp;

    public Pong(PingPong pp) {
        this.pp = pp;
    }

    @Override
    public void run() {
        while (true){
            try {
                pp.pong();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


