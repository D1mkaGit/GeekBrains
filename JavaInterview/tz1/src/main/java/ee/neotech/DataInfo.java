package ee.neotech;

public class DataInfo {
    private final String timestamp;

    public DataInfo(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "ee.neotech.DataInfo{" +
                "timestamp='" + timestamp + '\'' +
                '}';
    }
}
