package examples.autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CarApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        CarInterface car = (CarInterface) context.getBean(CarService.class);
        car.start();
        car.stop();
    }
}

