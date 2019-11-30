package lesson_5;

public class DeamoThread {
    public static void main(String[] args) {
        Thread timer = new Thread(new Runnable() {
            @Override
            public void run() {
                int sec = 0;

                while (true) {
                    sec++;
                    System.out.println("time " + sec);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        timer.setDaemon(true);
        timer.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("END");
    }
}
