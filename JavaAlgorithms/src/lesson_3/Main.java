package lesson_3;

public class Main {
    public static void main( String[] args ) {
//        MyStack<Integer> stack = new MyStack<>();
//
//        stack.push(1);
//        stack.push(2);
//        stack.push(3);
//        stack.push(4);
//  //      stack.push(5);
//
//        for (int i = 0; i < 5; i++) {
//            System.out.println(stack.pop());
//        }

//        Expression expression = new Expression("(4-6 + [3 + ) + 4 + {2*5}]");
//        System.out.println(expression.checkBracket());

//        MyQueue<Integer> queue = new MyQueue<>(5);
//        for (int i = 0; i < 5; i++) {
//            queue.insert(i);
//        }
//
//        for (int i = 0; i < 5; i++) {
//            System.out.println(queue.remove());
//        }

        MyPriorityQueue<Integer> mpq = new MyPriorityQueue<>();
        mpq.insert(7);
        System.out.println(mpq);

        mpq.insert(1);
        System.out.println(mpq);

        mpq.insert(3);
        System.out.println(mpq);

        System.out.println(mpq.remove());
        System.out.println(mpq);
    }
}
