package lesson_3.dz;

import lesson_3.MyStack;

public class StackTest {
    public static void main( String[] args ) {
        // стек с дефолтовым размером
        MyStack<Integer> stack = new MyStack<>();
        for (int i = 1; i <= 15; i++) {
            stack.push(i);
            if (stack.isFull()) stack.EnlargeStack(); // увеличиваем стек на дефолтовое
        }

        for (int i = 0; i < 15; i++) {
            System.out.print(stack.pop());
        }

        System.out.println(" ");

        // стек с указанным размером
        MyStack<Integer> stack2 = new MyStack<>(7);
        for (int i = 1; i <= 14; i++) {
            stack2.push(i);
            if (stack2.isFull()) stack2.EnlargeStack(7); // увеличиваем стек на указанное количество
        }

        for (int i = 0; i < 14; i++) {
            System.out.print(stack2.pop());
        }

        int numOfPushAttempts = 0;
        try {
            for (int i = 1; i <= 21; i++) {
                numOfPushAttempts = i;
                stack.push(i);
            }
        } catch (StackOverflowError e) {
            System.out.println("\nНевозможно засунуть в стек больше чем он есть, без увеличения");
            System.out.println("Длинна стека: " + stack.getSize() + ", Количество попыток вставить значение в стек: " + numOfPushAttempts);
        }

        numOfPushAttempts = 0;
        int additionalCapacity = 7;
        try {
            for (int i = 1; i <= 15; i++) {
                numOfPushAttempts = i;
                stack2.push(i);
                if (stack2.isFull())
                    stack2.EnlargeStack(additionalCapacity); // увеличиваем стек на указанное количество

            }
        } catch (StackOverflowError e) {
            System.out.println("\nНевозможно засунуть в стек больше чем он есть, даже если его увеличить " +
                    "на чуть чуть");
            System.out.println("Длинна стека с добавочными слотами: " + stack2.getSize() + ", Добавочные слоты в " +
                    "стеке: " + additionalCapacity + ", Количество " +
                    "попыток вставить значение в стек: " + numOfPushAttempts);
        }
    }
}
