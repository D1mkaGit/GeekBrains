package factory;

public class Main{
    public static void main(String[] args) {
        Car toyota = CarFactory.makeCar(30, "blue", new Engine());
        Car volvo = CarFactory.makeCar(50, "white", new Engine());
    }
}

interface Movable {
    void start();
    void stop();
}

class Engine {}

class Car implements Movable {
    int speed;
    String color;
    Engine engine;

    public Car(int speed, String color, Engine engine) {
        this.speed = speed;
        this.color = color;
        this.engine = engine;
    }

    @Override
    public void start() {
        while (speed<50) {
            speed++;
        }
    }

    @Override
    public void stop() {
        while (speed>0) {
            speed--;
        }
    }
}

class CarFactory {
    public static Car makeCar(int speed, String color, Engine engine){
        return new Car(speed, color, engine);
    }
}

