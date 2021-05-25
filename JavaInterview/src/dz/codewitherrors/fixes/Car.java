package dz.codewitherrors.fixes;

interface Movable {
    void move();
}

interface Stoppable {
    void stop();
}

abstract class Car implements Movable {
    private Engine engine;
    private String color;
    private String name;


    protected void start() {
        System.out.println("Car starting");
    }

    abstract void open();

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class LightWeightCar extends Car {

    @Override
    void open() {
        System.out.println("Car is open");
    }

    @Override
    public void move() {
        System.out.println("Car is moving");
    }

}

class Lorry extends Car implements Stoppable {

    @Override
    public void move(){
        System.out.println("Lorry is moving");
    }

    @Override
    public void stop(){
        System.out.println("Lorry is stop");
    }

    @Override
    void open() {

    }
}
