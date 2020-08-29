package ru.geekbrains.chat.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class UserRepository {
    private static Connection connection;
    private static Statement stmt;

    @Autowired
    public UserRepository(DataSource dataSource) throws SQLException {
        this(dataSource.getConnection());
    }

    public UserRepository(Connection connection) throws SQLException {
        UserRepository.connection = connection;
        stmt = connection.createStatement();
    }

    public static void addUser( String login, String pass, String nick ) {
        try {
            String query = "INSERT INTO main (login, password, nickname) VALUES (?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, login);
            ps.setInt(2, pass.hashCode());
            ps.setString(3, nick);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addUserToBlackListForSpecificUser( String nickWhoRequest, String nickToBlock ) {
        try {
            String query = "INSERT INTO blacklist (user_id, blocked_user_id) VALUES ((" +
                    "SELECT id FROM main where nickname = ?), (" +
                    "SELECT id FROM main where nickname = ?));";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, nickWhoRequest);
            ps.setString(2, nickToBlock);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeUserToBlackListForSpecificUser( String nickWhoRequest, String nickToBlock ) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM blacklist " +
                    "WHERE user_id = (SELECT id FROM main where nickname = ?) " +
                    "AND blocked_user_id = (SELECT id FROM main where nickname = ?)");
            ps.setString(1, nickWhoRequest);
            ps.setString(2, nickToBlock);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getBlacklist( String nickWhoRequest ) {
        String blackListString = getBlackList(nickWhoRequest).get(0);

        for (int i = 1; i < getBlackList(nickWhoRequest).size(); i++) {
            blackListString += ", " + getBlackList(nickWhoRequest).get(i);
        }

        return blackListString;
    }

    public static List<String> getBlackList( String nickWhoRequest ) {
        List<String> blockedNicks = new ArrayList<>();
        try {
            ResultSet nickNamesFromBD = stmt.executeQuery("SELECT id, nickname from main");
            HashMap<Integer, String> nickNamesMap = new HashMap();
            while (nickNamesFromBD.next()) {
                nickNamesMap.put(nickNamesFromBD.getInt(1), nickNamesFromBD.getString(2));
            }
            ResultSet rs = stmt.executeQuery("SELECT blacklist.blocked_user_id FROM main " +
                    "INNER JOIN blacklist on main.id = blacklist.user_id " +
                    "where nickname='" + nickWhoRequest + "'");
            while (rs.next()) {
                int bNId = rs.getInt(1);
                for (Integer key : nickNamesMap.keySet()) {
                    if (key == bNId)
                        blockedNicks.add(nickNamesMap.get(bNId));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blockedNicks;
    }

    public static String getNickByLoginAndPass( String login, String pass ) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT nickname, password FROM main WHERE login = '" + login + "'");
            int myHash = pass.hashCode(); // 137
            if (rs.next()) {
                String nick = rs.getString(1);
                int dbHash = rs.getInt(2);
                if (myHash == dbHash) {
                    return nick;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void log( LogEventType logEventType, String login ) {
        int logId = getLoginId(login);
        if (logId != 0) {
            try {
                String query = "INSERT INTO logs (id, event_type, login) VALUES (NULL, " + logEventType.eTypeId + ", ?);";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, String.valueOf(logId));
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // можно тут обработать/залогировать не существующие логины в базе
        }
    }

    private static int getLoginId( String login ) {
        int res = 0;
        try {
            ResultSet rs = stmt.executeQuery("SELECT id FROM main WHERE login = '" + login + "'");
            while (rs.next()) {
                System.out.println(rs.getInt(1));
                res = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static boolean checkIfNickIsAvailableInDb( String nick ) {
        try {
            ResultSet result = stmt.executeQuery("SELECT count(*) AS count FROM main where nickname='" + nick + "'");
            int count = result.getInt("count");
            if (count == 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkIfLoginIsAvailableInDb( String login ) {
        try {
            ResultSet result = stmt.executeQuery("SELECT count(*) AS count FROM main where login='" + login + "'");
            int count = result.getInt("count");
            if (count == 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public enum LogEventType {
        LOGIN(0),
        LOGOUT(1),
        INCORRECT_LOGIN(2);

        private final int eTypeId;

        LogEventType( int eTypeId ) {
            this.eTypeId = eTypeId;
        }
    }
}
