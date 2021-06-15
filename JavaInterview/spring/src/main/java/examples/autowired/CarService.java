package examples.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CarService {
    @Autowired
    @Qualifier("heavyCar")
    private CarInterface car;

    public CarInterface getCar() {
        return car;
    }

}
