package lesson_3.dz;

import java.io.IOException;

import static lesson_3.dz.MirroredText.getString;

public class MainDeque {
    public static void main( String[] args ) {
        String input = null;
        MyDeque<String> deque = null;
        while (true) {
            try {
                System.out.println("Введите текст, для сохранения в deque слева направа:");
                input = getString();
                if (!input.isEmpty()) {
                    deque = new MyDeque<>(input.length());
                    for (int i = 0; i < input.length(); i++) {
                        deque.insertRight(String.valueOf(input.charAt(i)));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert input != null;
            if (input.equals("q")) break;
            System.out.println("Текст в перевернутом виде (без удаления из deque):");
            for (int i = 0; i < input.length(); i++) {
                System.out.print(deque.getLast());
            }
            System.out.println("\nТекст в нормальном виде (с удалением из deque):");
            for (int i = 0; i < input.length(); i++) {
                System.out.print(deque.removeLeft());
            }
            System.out.println("\n\nВведите q - если хотите завершить работу программы");
        }
    }
}
