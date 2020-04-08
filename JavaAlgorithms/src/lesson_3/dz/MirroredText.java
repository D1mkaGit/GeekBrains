package lesson_3.dz;

import lesson_3.MyStack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//2. Создать программу, которая переворачивает вводимые строки (читает справа налево).

public class MirroredText {
    public static void main( String[] args ) {
        String input = null;
        MyStack<String> stack = null;
        while (true) {
            try {
                System.out.println("Введите текст, который нужно отзеркалить:");
                input = getString();
                if (!input.isEmpty()) {
                    stack = new MyStack<>(input.length());
                    for (int i = 0; i < input.length(); i++) {
                        stack.push(String.valueOf(input.charAt(i)));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert input != null;
            if (input.equals("q")) break;
            System.out.println("Текст в перевернутом виде:");
            for (int i = 0; i < input.length(); i++) {
                System.out.print(stack.pop());
            }
            System.out.println("\n\nВведите q - если хотите завершить работу программы");
        }
    }

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        return br.readLine();
    }
}