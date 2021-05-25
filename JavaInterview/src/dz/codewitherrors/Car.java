package dz.codewitherrors;

interface Moveable { //typo should be Movable
    void move();
}

interface Stopable { //typo should  be Stoppable
    void stop();
}

abstract class Car { //Movable should be implemented here
   /* public Engine engine; // not Implemented class Engine and should be private */
    private String color;
    private String name;


    protected void start() {
        System.out.println("Car starting");
    }

    abstract void open();

/*    public Engine getEngine() { // not Implemented class Engine and not imported
        return engine;
    }*/

/*    public void setEngine(Engine engine) { // not Implemented class Engine
        this.engine = engine;
    }*/

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

class LightWeightCar extends Car implements Moveable{ // Movable should be implemented in Car

    @Override
    void open() {
        System.out.println("LightWeightCar is open"); //it's LightWeightCar now, not a abstract car, change in text
    }

    @Override
    public void move() {
        System.out.println("LightWeightCar is moving"); //it's LightWeightCar now, not a abstract car, change in text
    }

}
/*
0 // extra artifact
class Lorry extends Car, Moveable, Stopable{ // interfacesh shouldbe implemented, not extended


    //@Override annotation should be here
    public void move(){
        System.out.println("Car is moving"); //it's lorry now, not a abstract car, change in text
    }

    //@Override annotation should be here
    public void stop(){
        System.out.println("Car is stop"); //it's lorry now, not a abstract car, change in text
    }


    // not implemented open class
}
        */
