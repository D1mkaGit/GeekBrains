import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class appIdentityMap {
    private static Connection connection;

    public static void connect() {
        try {
            String url = "jdbc:mysql://localhost:3306/archpatsys1?useSSL=false";
            String username = "test";
            String password = "test";

            System.out.println("Connecting database...");

            if (connection == null) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection(url, username, password);
                    System.out.println("Database connected!");
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        connect();

        IdentityMap identityMap = new IdentityMap(connection);

        // используем
        Client client = identityMap.getClient(1L);
        System.out.println(client);

        Client cl1 = new Client("testLogin", "testPassword", "testName", "testSurname", "test@test.ee");
        identityMap.insertClient(cl1);
        System.out.println(identityMap.getClient(cl1.getId()));

        cl1.setFirstName("changedName");
        identityMap.updateClient(cl1);
        System.out.println(identityMap.getClient(cl1.getId()));

        identityMap.deleteClient(cl1);

        disconnect();
    }
}
