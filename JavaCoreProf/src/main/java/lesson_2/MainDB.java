package lesson_2;

import java.sql.*;

public class MainDB {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement pstm;

    public static void main(String[] args) {
        try {
            connect();

//            ResultSet rs = stmt.executeQuery("SELECT id, name, score FROM students");
//
//            ResultSetMetaData rsmd = rs.getMetaData();
//
//            for (int i = 1; i < rsmd.getColumnCount(); i++) {
//                System.out.println(rsmd.getColumnName(i) + " " + rsmd.getColumnType(i));
//            }
//
//            while (rs.next()) {
//                System.out.println(rs.getInt(1) + " " + rs.getString("name"));
//            }

//            long t = System.currentTimeMillis();
//            connection.setAutoCommit(false);
//            for (int i = 0; i < 1000; i++) {
//                stmt.addBatch("INSERT INTO students (name, score)\n" +
//                        "VALUES ('Bob1', 10)");
//                stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob1', 10)");
//            }
//            stmt.executeBatch();
//            connection.setAutoCommit(true);
//            System.out.println(System.currentTimeMillis() - t);

//            int res = stmt.executeUpdate("CREATE TABLE students (" +
//                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
//                    "name TEXT," +
//                    "score INTEGER)");
//            System.out.println(res);

//            connection.setAutoCommit(false);
//            pstm = connection.prepareStatement("INSERT INTO students (name, score) VALUES (?, ?)");
//            for (int i = 0; i < 1000; i++) {
//                pstm.setString(1, "Bob " + i);
//                pstm.setInt(2, 20 + i);
//                pstm.addBatch();
//            }
//            pstm.executeBatch();
//            connection.setAutoCommit(true);

            Savepoint spl = connection.setSavepoint();
            try {
                stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob1', 10)");
                stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob2', 20)");
                stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob3', 30)");
            } catch (Exception e) {
                connection.rollback(spl);
            }

            connection.setAutoCommit(true);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        // работа с БД
    }

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:main.db");
        stmt = connection.createStatement();
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
