package counter;

import static counter.Parking.PARKING_PLACE;
import static counter.Parking.SEMAPHORE;

class Car implements Runnable {
    private int carNumber;

    public Car(int carNumber) {
        this.carNumber = carNumber;
    }

    @Override
    public void run() {
        System.out.println("Автомобиль №" + carNumber + " подъехал к парковке ");
        try {
            SEMAPHORE.acquire();
            int parkingNumber = -1;

            synchronized (PARKING_PLACE) {
                for (int i = 0; i < 5; i++) {
                    if (!PARKING_PLACE[i]) {
                        PARKING_PLACE[i] = true;
                        parkingNumber = i;
                        System.out.println("Машина №" + carNumber + " на месте № " + parkingNumber);
                        break;
                    }
                }

            }
            Thread.sleep(5000);
            synchronized (PARKING_PLACE) {
                PARKING_PLACE[parkingNumber] = false;
            }
            SEMAPHORE.release();
            System.out.println("Машина № " + carNumber + " уехала");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
