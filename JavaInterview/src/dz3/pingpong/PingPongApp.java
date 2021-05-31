package dz3.pingpong;

public class PingPongApp {
    public static void main(String[] args) throws InterruptedException {
        PingPong pp = new PingPong();
        new Thread(new Ping(pp)).start();
        new Thread(new Pong(pp)).start();

    }
}
