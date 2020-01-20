package lesson_2.dz;

import java.sql.*;
import java.util.Scanner;

public class Main_Prepod {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement psInsert;

    // clear uncommited queries
    // TEXT vs VARCHAR vs STRING

    public static void main( String[] args ) {
        try {
            connect();
            createTableEx();
            clearTableEx();

            //  запускаем все 3 варианта, заполнения, поочереди
            preparedStmtEx();
            //    batchEx();
            //  transactionEx();

            // ищем
            selectEx();

            // изменяем
            updateEx();

            // ищем диапазон
            selectExRange();

            // откат
            //  rollbackEx();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    private static void rollbackEx() throws SQLException {
        stmt.executeUpdate("INSERT INTO goodsTable (prodid, title, cost) VALUES ('101101', 'car', 50000);");
        Savepoint sp1 = connection.setSavepoint();
        stmt.executeUpdate("INSERT INTO goodsTable (prodid, title, cost) VALUES ('102102', 'car', 50000);");
        connection.rollback(sp1);
        stmt.executeUpdate("INSERT INTO goodsTable (prodid, title, cost) VALUES ('103103', 'car', 50000);");
        connection.setAutoCommit(true);
    }

    // заполняем таблицу вар3
    private static void preparedStmtEx() throws SQLException {
        long t = System.currentTimeMillis();
        connection.setAutoCommit(false);
        psInsert = connection.prepareStatement("INSERT INTO goodsTable (prodid, title, cost) VALUES (?, ?, ?);");
        for (int i = 0; i < 5000; i++) {
            psInsert.setString(1, (i + 1) + "1");
            psInsert.setString(2, "car");
            psInsert.setInt(3, 20 + (i * 10) % 90);
            psInsert.addBatch();
        }
        psInsert.executeBatch();
        connection.setAutoCommit(true);
        System.out.println(System.currentTimeMillis() - t);
    }

    // заполняем таблицу вар2
    private static void batchEx() throws SQLException {
        connection.setAutoCommit(false);
        long t = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            stmt.addBatch("INSERT INTO goodsTable (prodid, title, cost) VALUES (" + i + ", 'car', 100);");
        }
        stmt.executeBatch();
        connection.setAutoCommit(true);
        System.out.println(System.currentTimeMillis() - t);
    }

    // заполняем таблицу вар1
    private static void transactionEx() throws SQLException {
        connection.setAutoCommit(false);
        long t = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            stmt.executeUpdate("INSERT INTO goodsTable (prodid, title, cost) VALUES (" + i + ", 'car', 100);");
        }
        System.out.println(System.currentTimeMillis() - t);
        connection.setAutoCommit(true);
    }

    // 1 создаем таблицу
    private static void createTableEx() throws SQLException {

        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS goodsTable (\n" +
                "    id    INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    prodid  TEXT UNIQUE,\n" +
                "    title TEXT,\n" +
                "    cost INTEGER\n" +
                ");");
    }


    // уничтожить таблицу
    private static void dropTableEx() throws SQLException {
        stmt.executeUpdate("DROP TABLE IF EXISTS goodsTable;");
    }


    // чистим таблицу
    private static void clearTableEx() throws SQLException {
        stmt.executeUpdate("DELETE FROM goodsTable;");
    }

    private static void deleteOneEntryEx() throws SQLException {
        stmt.executeUpdate("DELETE FROM goodsTable WHERE id = 5;");
    }

    // изменяем
    private static void updateEx() throws SQLException {
        System.out.println("изменяем");
        Scanner scanner = new Scanner(System.in);
        String cost = scanner.nextLine();
        String id = scanner.nextLine();
        String sql = String.format("UPDATE goodsTable SET cost = '%s' WHERE id = '%s';", cost, id);
        stmt.executeUpdate(sql);
    }

    private static void insertEx() throws SQLException {
        stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob4', 100);");
    }

    // узнаем цену товара
    private static void selectEx() throws SQLException {
        System.out.println("ищем");
        Scanner scanner = new Scanner(System.in);
        String res = scanner.nextLine();
        String sql = String.format("SELECT cost FROM goodsTable where prodid = '%s';", res);
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString("cost"));
        }
    }

    // ищем в диапазоне
    private static void selectExRange() throws SQLException {
        System.out.println("ищем в диапазоне");
        Scanner scanner = new Scanner(System.in);
        String low = scanner.nextLine();
        String hight = scanner.nextLine();
        String sql = String.format("SELECT prodid FROM goodsTable where cost BETWEEN '%s' AND '%s' ORDER BY  prodid;", low, hight);
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString("prodid"));
        }
    }

    public static void connect() throws Exception {
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