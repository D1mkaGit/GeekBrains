package ru.geekbrains.chat.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMainViaAnnotations {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("spring-context-via-annotations.xml");
    }
}
