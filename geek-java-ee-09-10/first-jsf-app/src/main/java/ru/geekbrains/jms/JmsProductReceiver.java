package ru.geekbrains.jms;

import ru.geekbrains.dto.ProductRemoteDto;
import ru.geekbrains.service.ProductService;
import ru.geekbrains.service.dto.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/jms/queue/productQueue")
        }
)
public class JmsProductReceiver implements MessageListener {

    private final Logger logger = LoggerFactory.getLogger(JmsProductReceiver.class);

    @EJB
    private ProductService productService;

    @Override
    public void onMessage(Message message) {
        logger.info("New JMS message");

        if (message instanceof ObjectMessage) {
            ObjectMessage om = (ObjectMessage) message;
            try {
                ProductRemoteDto product = (ProductRemoteDto) om.getObject();
                productService.save(new ProductDto(product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getCategoryId(),
                        null,
                        product.getBrandId(),
                        null));
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
