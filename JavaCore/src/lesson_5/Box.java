package lesson_5;

public class Box {

    Object object = new Object();

    void doSomeThing() {
        System.out.println(1);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (object) {
            System.out.println(998);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(999);
        }

        System.out.println(2);
    }
}


class MainBox {
    public static void main(String[] args) {
        Box box = new Box();

        new Thread(new Runnable() {
            @Override
            public void run() {
                box.doSomeThing();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                box.doSomeThing();
            }
        }).start();
    }
}