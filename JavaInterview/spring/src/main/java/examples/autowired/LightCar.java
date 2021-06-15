package examples.autowired;

import org.springframework.stereotype.Component;

@Component("lightCar")
public class LightCar implements CarInterface{
    @Override
    public void start() {
        System.out.println("LightCar run");
    }

    @Override
    public void stop() {
        System.out.println("LightCar stop");
    }
}
