import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

class IdentityMap {
    private final HashMap<Long, Client> clientsMap = new HashMap<>();
    final ClientMapper clientMapper;

    public IdentityMap(Connection connection) throws SQLException {
        clientMapper = new ClientMapper(connection);
        int clientsInDb = clientMapper.findAllClients().size();
        if (clientsInDb > 0)
            for (int i = 0; i < clientsInDb; i++) {
                addClientToMap(clientMapper.findAllClients().get(i));
            }
    }


    public void addClientToMap(Client client) {
        clientsMap.put(client.getId(), client);
    }

    public void insertClient(Client client) throws SQLException {
        clientMapper.insert(client);
        client.setId(clientMapper.findIdByLogin(client.getLogin())); // вытаскиваем id созданного
        addClientToMap(client);
    }

    public Client getClient(Long key) {
        return clientsMap.get(key);
    }

    public void updateClient(Client client) {
        addClientToMap(client);
        clientMapper.update(client);
    }

    public void deleteClient(Client client) {
        clientsMap.remove(client.getId(), client);
        clientMapper.delete(client);
    }
}
