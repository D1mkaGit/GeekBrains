package ee.neotech;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Queue;


public class DataGenerating implements Runnable {

    private final Queue<DataInfo> messages;

    public DataGenerating(Queue<DataInfo> messages) {
        this.messages = messages;
    }

    @Override
    public void run() {
        DataInfo data = generateData();
        messages.add(data);
    }

    private DataInfo generateData() {
        return new DataInfo(String.valueOf(Timestamp.from(Instant.now())));
    }

}
