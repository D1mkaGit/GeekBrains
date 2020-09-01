package ru.geekbrains.chat.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("spring-context.xml");
    }
}
