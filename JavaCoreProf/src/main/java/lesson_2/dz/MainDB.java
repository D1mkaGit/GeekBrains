package lesson_2.dz;

import java.sql.*;

/**
 * 1. Сделать методы для работы с БД (CREATE, INSERT, UPDATE, DELETE, SELECT)
 */

public class MainDB {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement pstm;

    public static void main(String[] args) {
        try {
            connect();
            Savepoint spl = connection.setSavepoint();
            try {
//                createTable("homework");

//                insertData("text",1212);
//                insertData("text",112);
//                insertData("test",122);

//                updateDataById(1, "changed", 999);
//                updateDataByText("text", "changedByTExt", 888);
//                updateDataByValue(1212, "chnagedByValue", 666);

//                deleteDataById(2);
//                deleteDataByText("tst");
//                deleteDataByValue(123);

                selectDataById(1);
                selectDataByText("tst");
                selectDataByValue(122);

                // working here

            } catch (Exception e) {
                connection.rollback(spl);
                System.out.println(e.getMessage());
            }
            connection.setAutoCommit(true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    private static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:main.db");
        stmt = connection.createStatement();
    }

    private static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable(String tableName) throws SQLException {
        stmt.executeUpdate("CREATE TABLE " + tableName + "(\n" +
                "    id    INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    text  TEXT,\n" +
                "    value INTEGER\n" +
                ");");
    }

    private static void insertData(String text, int value) throws SQLException {
        stmt.executeUpdate("INSERT INTO homework VALUES(NULL,'" + text + "', " + value + ")");
    }

    private static void updateDataById(int id, String textToChange, int valueToChange) throws SQLException {
        stmt.executeUpdate("UPDATE homework SET text = '" + textToChange + "', value = " + valueToChange + " WHERE id = " + id);
    }

    private static void updateDataByText(String text, String textToChange, int valueToChange) throws SQLException {
        stmt.executeUpdate("UPDATE homework SET text = '" + textToChange + "', value = " + valueToChange + " WHERE text = '" + text + "'");
    }

    private static void updateDataByValue(int value, String textToChange, int valueToChange) throws SQLException {
        stmt.executeUpdate("UPDATE homework SET text = '" + textToChange + "', value = " + valueToChange + " WHERE value = " + value);
    }

    private static void deleteDataAll() throws SQLException {
        stmt.executeUpdate("DELETE FROM homework");
    }

    private static void deleteDataById(int id) throws SQLException {
        stmt.executeUpdate("DELETE FROM homework WHERE id = " + id);
    }

    private static void deleteDataByText(String text) throws SQLException {
        stmt.executeUpdate("DELETE FROM homework WHERE text = '" + text + "'");
    }

    private static void deleteDataByValue(int value) throws SQLException {
        stmt.executeUpdate("DELETE FROM homework WHERE value = " + value);
    }

    private static void selectDataById(int id) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT id, text, value  FROM homework WHERE ID = " + id);
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString("text") + " " + rs.getInt(3));
        }
    }

    private static void selectDataByText(String text) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT id, text, value  FROM homework WHERE Text = '" + text + "'");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString("text") + " " + rs.getInt(3));
        }
    }

    private static void selectDataByValue(int value) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT id, text, value  FROM homework WHERE value = " + value);
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString("text") + " " + rs.getInt(3));
        }
    }

}
