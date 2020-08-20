package ru.geekbrains.chat.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public Server Server() {
        return new Server();
    }

}
