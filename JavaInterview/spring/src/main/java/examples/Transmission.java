package examples;

public class Transmission {
    private String type;

    public Transmission(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transmission{" +
                "type='" + type + '\'' +
                '}';
    }
}
