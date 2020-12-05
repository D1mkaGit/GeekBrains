package ru.geekbrains;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages ="ru.geekbrains")
public class ShopPictureServiceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopPictureServiceAppApplication.class, args);
	}

}
