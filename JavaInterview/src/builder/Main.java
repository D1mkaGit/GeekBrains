package builder;

public class Main{
    public static void main(String[] args) {
        Car c = new Car.Builder().addEngine(new Engine()).addPedals(new Pedals()).build();

    }
}

class Engine{}
class Transmission{}
class Body{}
class Brakes{}
class Pedals{}
class WindscreenWipers{}
class Wheels{}
class Radio{}
class Seats{}
class Belts{}
class ClimateControl{}

class Car {
    private Engine engine;
    private Transmission transmission;
    private Body body;
    private Brakes brakes;
    private Pedals pedals;

    public static class Builder {
        private Engine engine;
        private Transmission transmission;
        private Body body;
        private Brakes brakes;
        private Pedals pedals;

        public Builder addEngine(Engine engine) {
            this.engine = engine;
            return this;
        }

        public Builder addTransmission(Transmission transmission) {
            this.transmission = transmission;
            return this;
        }

        public Builder addBody(Body body) {
            this.body = body;
            return this;
        }

        public Builder addBrakes(Brakes brakes) {
            this.brakes = brakes;
            return this;
        }

        public Builder addPedals(Pedals pedals) {
            this.pedals = pedals;
            return this;
        }

        public Car build(){
            return new Car();
        }
    }

}

