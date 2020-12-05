package ru.geekbrains;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages ="ru.geekbrains")
//@Import(ShopAdminUiApplication.class)
public class ShopUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopUiApplication.class, args);
	}
}
