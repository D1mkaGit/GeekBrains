package lesson_3.dz;

import lesson_3.MyQueue;

public class QueueTest {
    public static void main( String[] args ) {
        int sizeForTest = 5;
        int queueSize = 5;

        // очередь с заданным размером
        MyQueue<Integer> queue = new MyQueue<>(queueSize);
        for (int i = 0; i < sizeForTest; i++) {
            queue.insert(i);
        }
        System.out.println("Очередь с размером: " + queue.size());
        for (int i = 0; i < sizeForTest; i++) {
            System.out.print(queue.remove());
        }

        try {
            for (int i = 0; i < sizeForTest + 1; i++) {
                queue.insert(i);
            }
        } catch (StackOverflowError e) {
            System.out.println("\nНевозможно засунуть в очередь больше чем она есть");
            System.out.println("Длинна очереди: " + queue.size() + ", Количество попыток вставить значение в стек:" +
                    " " + (sizeForTest + 1));
        }

        System.out.println(" ");
        // очередь с дефолтовым размером
        sizeForTest = 10;
        MyQueue<Integer> queue2 = new MyQueue<>();
        for (int i = 0; i < sizeForTest; i++) {
            queue2.insert(i);
        }
        System.out.println("Очередь с размером: " + queue2.size());
        for (int i = 0; i < sizeForTest; i++) {
            System.out.print(queue2.remove());
        }

        try {
            for (int i = 0; i < sizeForTest + 1; i++) {
                queue2.insert(i);
            }
        } catch (StackOverflowError e) {
            System.out.println("\nНевозможно засунуть в очередь больше чем она есть");
            System.out.println("Длинна очереди: " + queue2.size() + ", Количество попыток вставить значение в стек:" +
                    " " + (sizeForTest + 1));
        }
    }
}
