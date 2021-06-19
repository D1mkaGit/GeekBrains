package ee.neotech;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import java.sql.*;
import java.util.Queue;
import java.util.logging.Logger;

public class DataToDb implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(DataToDb.class.getName());
    private static final String URL = "jdbc:mysql://localhost/tz1?createDatabaseIfNotExist=true&autoReconnect=true&connectTimeout=5";
    private static final String USERNAME = "test";
    private static final String PASSWORD = "test";
    private static final String TABLE_NAME = "test";

    private final Queue<DataInfo> messages;

    public DataToDb(Queue<DataInfo> messages) {
        this.messages = messages;
    }

    @Override
    public void run() {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement stmt = connection.createStatement()) {
            while (messages.iterator().hasNext()) {
                DataInfo timestampFromBuffer = messages.iterator().next();
                if (insertData(timestampFromBuffer.getTimestamp(), connection, stmt))
                    messages.remove(timestampFromBuffer);
                else
                    break;
            }
        } catch (SQLNonTransientConnectionException exception) {
            LOGGER.warning("Connection to DB unavailable. Reconnecting...");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }


    public void createTableIfNotExist() {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement stmt = connection.createStatement()) {
            //dropTable(stmt);
            createTableIfNotExists(stmt);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void getAllDataInfo() {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement stmt = connection.createStatement()) {
            selectAll(stmt);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    private void createTableIfNotExists(Statement stmt) throws SQLException {
        LOGGER.info("Creating table " + TABLE_NAME + " if not exist");
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `timestamp` TIMESTAMP NOT NULL,\n" +
                "  PRIMARY KEY (`id`));");
    }

    private void dropTable(Statement stmt) throws SQLException {
        stmt.executeUpdate("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
    }

    private void selectAll(Statement stmt) throws SQLException {
        String sql = "SELECT timestamp FROM " + TABLE_NAME + ";";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString("timestamp"));
        }
    }

    private boolean insertData(String timestamp, Connection connection, Statement stmt) {
        LOGGER.info("adding to db: " + timestamp);
        String sql = "INSERT INTO " + TABLE_NAME + " VALUES(NULL,?);";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            //stmt.executeQuery("SELECT SLEEP(" + new Random().nextInt(2) + ");"); //emulating from 0 to 2 seconds delay during SQL operation
            pstmt.setString(1, timestamp);
            pstmt.executeUpdate();
            return true;
        } catch (CommunicationsException exception) {
            LOGGER.warning("Connection Lost! Reconnecting...");
            return false;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
    }
}
