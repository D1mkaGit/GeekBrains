package ru.geekbrains;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("ru.geekbrains")
public class Config {

    @Bean
    public Student student(){
        return new Student();
    }
}
