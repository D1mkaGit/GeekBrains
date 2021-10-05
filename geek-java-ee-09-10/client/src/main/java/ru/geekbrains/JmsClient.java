package ru.geekbrains;

import ru.geekbrains.dto.ProductRemoteDto;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

public class JmsClient {

    public static void main(String[] args) throws NamingException, IOException {
        Context context = createInitialContext();

        ConnectionFactory factory = (ConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
        JMSContext jmsContext = factory.createContext("jmsuser", "123");

        Destination dest = (Destination) context.lookup("jms/queue/productQueue");

        JMSProducer producer = jmsContext.createProducer();

        ObjectMessage msg = jmsContext.createObjectMessage(new ProductRemoteDto(null,
                "JMS Product",
                new BigDecimal(123),
                4L,
                null,
                2L,
                null));

        producer.send(dest, msg);
    }

    public static Context createInitialContext() throws IOException, NamingException {
        final Properties env = new Properties();
        env.load(EjbClient.class.getClassLoader().getResourceAsStream("wildfly-jndi.properties"));
        return new InitialContext(env);
    }
}
