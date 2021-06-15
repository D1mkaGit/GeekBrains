package examples;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("examples")
public class Config {
    @Bean
    public Engine engine(){
        return new Engine("v4", 2);
    }

    @Bean
    public Transmission transmission(){
        return new Transmission("robot");
    }
}

