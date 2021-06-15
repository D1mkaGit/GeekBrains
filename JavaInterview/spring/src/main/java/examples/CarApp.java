package examples;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CarApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        //ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        Car car = context.getBean(Car.class);
        System.out.println(car);

        Carr car1 = context.getBean(Carr.class);
        car1.setName("Toyota");

        Carr car2 = context.getBean(Carr.class);
        car2.setName("BMW");

        System.out.println("car_1: " + car1.getName() + "\n car_2: " + car2.getName());
    }
}



