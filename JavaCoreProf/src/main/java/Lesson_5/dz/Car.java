package lesson_5.dz;

import java.util.concurrent.Semaphore;

public class Car implements Runnable {
    private static int CARS_COUNT;


    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;

    public Car( Race race, int speed ) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void run() {
        try {

            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startRace( int carCount ) {
        Semaphore smp = new Semaphore(carCount / 2);
        for (int i = 0; i < race.getStages().size(); i++) {
            if (race.getStages().get(i).toString().contains("Тоннель")) {

                try {
                    race.getStages().get(i).go(this);
                    smp.acquire();

                } catch (InterruptedException e) {
                    e.printStackTrace();

                } finally {
                    smp.release();
                }
            }
            race.getStages().get(i).go(this);
        }
    }
}
