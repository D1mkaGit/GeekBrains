package examples;

public class Engine {
    private String engineType;
    private int engineVolume;

    public Engine(String engineType, int engineVolume) {
        this.engineType = engineType;
        this.engineVolume = engineVolume;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public int getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(int engineVolume) {
        this.engineVolume = engineVolume;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "engineType='" + engineType + '\'' +
                ", engineVolume=" + engineVolume +
                '}';
    }
}
