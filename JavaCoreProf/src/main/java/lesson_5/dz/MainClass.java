package lesson_5.dz;

import java.util.concurrent.locks.ReentrantLock;

// Все участники должны стартовать одновременно,
// несмотря на то, что на подготовку у каждого их них уходит разное время.
public class MainClass {
    public static final int CARS_COUNT = 4;


    public static void main( String[] args ) {

        ReentrantLock r1 = new ReentrantLock();
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        new Thread(() -> {
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");


            for (int i = 0; i < cars.length; i++) {
                cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
            }

            for (Car car : cars) {
                new Thread(car).start();
            }
        }).start();
        while (Thread.activeCount() > 2) r1.lock(); // лочим треды, пока их количество не уменьшится, что будет
        // означать, что можно идти дальше.
        r1.unlock();


        new Thread(() -> {
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            for (int i = 0; i < cars.length; i++) {
                final int w = i;
                new Thread(() -> cars[w].startRace()).start();
            }

        }).start();
        while (Thread.activeCount() > 2) r1.lock();// лочим треды, пока их количество не уменьшится, что будет
        // означать, что можно идти дальше
        r1.unlock();
        new Thread(() -> System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!")).start();
    }
}