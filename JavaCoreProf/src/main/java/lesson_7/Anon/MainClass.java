package lesson_7.Anon;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.HashMap;

public class MainClass {

    static Connection connection;
    static Statement stmt;

    public static void connect() throws Exception {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:main.db");
        stmt = connection.createStatement();
    }

    public static void main( String[] args ) throws SQLException {
        prepareTable(Student.class);
        Student s = new Student(1, "Bob", 100, "bob@gmail.com");
        addObject(s);
    }

    public static void disconnect() throws SQLException {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void prepareTable( Class c ) throws SQLException {
        if (!c.isAnnotationPresent(XTable.class)) throw new RuntimeException("XTable missed");

        // если у нас есть поле int то напишем его как "INTEGER" и т.д.
        HashMap<Class, String> hm = new HashMap<>();
        hm.put(int.class, "INTEGER");
        hm.put(String.class, "TEXT");

        try {
            connect();
            // узнаем имя таблицы
            String tableName = ((XTable) c.getAnnotation(XTable.class)).name();
            // если такая таблица есть то ее дропнем
            stmt.executeUpdate("DROP TABLE IF EXISTS " + tableName + ";");

            // создадим таблицу  (но мы не можем сразу собрать запрос, соберем по кускам)
// 'CREATE TABLE IF NOT EXISTS students (id INTEGER, name TEXT, score INTEGER, email TEXT);'

            String query = "CREATE TABLE IF NOT EXISTS " + tableName + " (";
            // 'CREATE TABLE IF NOT EXISTS students ('
            // получаем все поля из класса
            Field[] fields = c.getDeclaredFields();
            for (Field o : fields) {
                // если есть анотация то добавляем ее в таблицу
                if (o.isAnnotationPresent(XField.class)) {
                    // если написать o.getName() + o.getType()
                    // будет проблема так как у нас String а нужен Text
                    // создадим HM которая преобразует класс к строке
                    query += o.getName() + " " + hm.get(o.getType()) + ", ";
                }
            }
            // 'CREATE TABLE IF NOT EXISTS students (id INTEGER, name TEXT, score INTEGER, email TEXT, ' // обрежем строку
            query = query.substring(0, query.length() - 2);
            // 'CREATE TABLE IF NOT EXISTS students (id INTEGER, name TEXT, score INTEGER, email TEXT'
            query += ");";
            // 'CREATE TABLE IF NOT EXISTS students (id INTEGER, name TEXT, score INTEGER);'
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public static void addObject( Object obj ) throws SQLException {
        Class c = obj.getClass();
        if (!c.isAnnotationPresent(XTable.class)) throw new RuntimeException("XTable missed");
        try {
            connect();
            String tableName = ((XTable) c.getAnnotation(XTable.class)).name();
            // 'INSERT INTO students (id, name, score, email, salary) VALUES (...);'
            String query = "INSERT INTO " + tableName + " (";
            // 'INSERT INTO students ('
            Field[] fields = c.getDeclaredFields();
            for (Field o : fields) {
                if (o.isAnnotationPresent(XField.class)) {
                    query += o.getName() + ", ";
                }
            }
            // 'INSERT INTO students (id, name, score, email, '
            query = query.substring(0, query.length() - 2);
            // 'INSERT INTO students (id, name, score, email'
            query += ") VALUES (";
            // 'INSERT INTO students (id, name, score, email, salary) VALUES ('
            for (Field o : fields) {
                if (o.isAnnotationPresent(XField.class)) {
                    query += "?, ";
                }
            }
            // 'INSERT INTO students (id, name, score, email) VALUES (?, ?, ?, ?, '
            query = query.substring(0, query.length() - 2);
            // 'INSERT INTO students (id, name, score, email) VALUES (?, ?, ?, ?'
            query += ");";
            // 'INSERT INTO students (id, name, score, email) VALUES (?, ?, ?, ?);'
            PreparedStatement ps = connection.prepareStatement(query);
            int counter = 1;
            for (Field o : fields) {
                if (o.isAnnotationPresent(XField.class)) {
                    ps.setObject(counter, o.get(obj));
                    counter++;
                }
            }
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }
}
