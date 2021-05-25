package proxy;

public class Main {
    public static void main(String[] args) {
        CarProxy c = new CarProxy(new Car(), true);
        c.speedUp();
        c.speedDown();
    }
}

interface Movable {
    void speedUp();
    void speedDown();
}

class Car implements Movable {
    int speed;

    @Override
    public void speedUp() {
        while (speed < 50) {
            speed++;
        }
    }

    @Override
    public void speedDown() {
        while (speed > 0) {
            speed--;
        }
    }
}

class CarProxy {
    Car car;
    boolean haveLicense;

    public CarProxy(Car car, boolean haveLicense) {
        this.car = car;
        this.haveLicense = haveLicense;
    }

    public void speedUp() {
        if (haveLicense) {
            car.speedUp();
        }
    }

    public void speedDown() {
        if(haveLicense) {
            car.speedDown();
        }
    }
}

