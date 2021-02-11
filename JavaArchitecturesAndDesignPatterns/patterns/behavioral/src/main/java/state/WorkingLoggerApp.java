package state;

public class WorkingLoggerApp {
    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker();

        // приходит на работу отмечается
        worker.doActivity();

        Thread.sleep(1000); // имитируем работу

        // идет на обед
        worker.doActivity();

        Thread.sleep(1000); // имитируем обед

        // возвращаемся с обеда
        worker.doActivity();

        Thread.sleep(1000); // имитируем работу

        worker.doActivity(); // заканчиваем работу идем домой
    }

}
