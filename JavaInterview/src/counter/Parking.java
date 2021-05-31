package counter;

import java.util.concurrent.Semaphore;

public class Parking {
    static final boolean[] PARKING_PLACE = new boolean[5];

    static final Semaphore SEMAPHORE = new Semaphore(5,true);

    public static void main(String[] args) {
        for (int i = 0; i <7 ; i++) {
            new Thread(new Car(i)).start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


