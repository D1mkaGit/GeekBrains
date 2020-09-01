package ru.geekbrains.chat.server;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringMainViaAnnotationsClass {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(SpringConfig.class);
    }
}
