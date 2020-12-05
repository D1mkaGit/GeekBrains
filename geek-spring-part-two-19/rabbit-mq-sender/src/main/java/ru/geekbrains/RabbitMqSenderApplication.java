package ru.geekbrains;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitMqSenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitMqSenderApplication.class, args);
	}

}
