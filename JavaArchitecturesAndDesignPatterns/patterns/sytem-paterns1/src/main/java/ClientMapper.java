import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientMapper {
    private final Connection connection;

    public ClientMapper(Connection connection) {
        this.connection = connection;
    }

    public Client findById(Long idClient) throws SQLException, RecordNotFoundException {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT `id`, `login`, `password`, `firstName`, `lastName`, `email` FROM `clients` WHERE `id` = ?");
        statement.setLong(1, idClient);
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getLong(1));
                client.setLogin(resultSet.getString(2));
                client.setPassword(resultSet.getString(3));
                client.setFirstName(resultSet.getString(4));
                client.setLastName(resultSet.getString(5));
                client.setEmail(resultSet.getString(6));
                return client;
            }
        }
        throw new RecordNotFoundException(idClient);
    }

    public Long findIdByLogin(String login) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT `id` FROM `clients` WHERE login = '" + login + "'");
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                return resultSet.getLong(1);
            }
        }
        throw new RecordNotFoundException(login);
    }

    public void insert(Client client) throws SQLException {
        if (isExistsByLogin(client.getLogin())) {
            throw new RecordNotFoundException(client.getLogin());
        } else {
            try {
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO `clients` (`login`, `password`, `firstName`, `lastName`, `email`) " +
                                "VALUES ('" + client.getLogin() + "', " +
                                "'" + client.getPassword() + "'," +
                                "'" + client.getFirstName() + "'," +
                                "'" + client.getLastName() + "', " +
                                "'" + client.getEmail() + "')");
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(Client client) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE `clients` " +
                            "SET `login` = '" + client.getLogin() + "', " +
                            "`password` = '" + client.getPassword() + "', " +
                            "`firstName` = '" + client.getFirstName() + "', " +
                            "`lastName` = '" + client.getLastName() + "', " +
                            "`email` = '" + client.getEmail() + "' " +
                            "WHERE (`id` = '" + client.getId() + "');\n");
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Client client) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM `archpatsys1`.`clients` WHERE (`id` = '" + client.getId() + "')");
            statement.execute();
            System.out.println("User with id " + client.getId() + " was deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isExistsById(Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT count(*) FROM `clients` WHERE id = '" + id + "'");
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                return resultSet.getInt(1) == 1;
            }
        }
        throw new RecordNotFoundException(id);
    }

    public boolean isExistsByLogin(String login) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT count(*) FROM `clients` WHERE login = '" + login + "'");
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                return resultSet.getInt(1) == 1;
            }
        }
        throw new RecordNotFoundException(login);
    }


    public static class RecordNotFoundException extends RuntimeException {
        public RecordNotFoundException(Long id) {
            super("Client with id: " + id + " was not found.");
        }

        public RecordNotFoundException(String login) {
            super("Client with login: " + login + " was not found.");
        }
    }
}
