package singleton;

public class Main {
    public static void main(String[] args) {
        internalQuery();
        new ExternalClass();
        internalQuery();
    }

    private static void internalQuery() {
        ConnectToDB.getInstance();
        System.out.println("Резульат квери в базу");
    }

}

class ExternalClass {
    public ExternalClass() {
        ConnectToDB.getInstance();
        System.out.println("Результат стороннего квери в базу");
    }
}
