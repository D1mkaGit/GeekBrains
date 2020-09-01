package ru.geekbrains.chat.server;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringMainViaAnnotationsClassAndScan {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(SpringConfigWithScan.class);
    }
}
