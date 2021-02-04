package singleton;

public class ConnectToDB {
    private static volatile ConnectToDB instance;

    private ConnectToDB() {
        System.out.println("тут идет звук соединения с модемом");
    }

    public static ConnectToDB getInstance() {
        ConnectToDB localInstance = instance;
        if (localInstance == null) {
            synchronized (ConnectToDB.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ConnectToDB();
                }
            }
        }
        return localInstance;
    }

}
