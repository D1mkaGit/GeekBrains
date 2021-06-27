package ee.neotech;

import java.util.Queue;
import java.util.concurrent.*;

public class DataApp {
    public static void main(String[] args) {
        Queue<DataInfo> messages = new ConcurrentLinkedQueue<>();
        DataGenerating generator = new DataGenerating(messages);
        DataToDb dataToDb = new DataToDb(messages);

        dataToDb.createTableIfNotExist();

        if (args.length > 0) {
            for (String arg : args) {
                if (arg.equals("-p")) {
                    dataToDb.getAllDataInfo();
                }
            }
        } else {
            ScheduledExecutorService execServiceForGenerator = Executors.newScheduledThreadPool(1);
            execServiceForGenerator.scheduleAtFixedRate(generator, 0, 1000L, TimeUnit.MILLISECONDS);

            ScheduledExecutorService execServiceForDbThreads = Executors.newScheduledThreadPool(1);
            execServiceForDbThreads.scheduleAtFixedRate(dataToDb, 0, 1000L, TimeUnit.MILLISECONDS);
        }
    }
}
