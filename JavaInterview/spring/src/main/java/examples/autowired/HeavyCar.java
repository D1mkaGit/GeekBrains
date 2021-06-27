package examples.autowired;

import org.springframework.stereotype.Component;

@Component("heavyCar")
public class HeavyCar implements CarInterface {
    @Override
    public void start() {
        System.out.println("HeavyCar run");

    }

    @Override
    public void stop() {
        System.out.println("HeavyCar stop");
    }
}
