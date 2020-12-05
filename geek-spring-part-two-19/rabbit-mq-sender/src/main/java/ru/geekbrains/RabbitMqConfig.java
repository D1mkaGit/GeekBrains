package ru.geekbrains;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${user.name}")
    private String userName;

    @Bean
    Queue userQueue() {
        return new Queue(userName + ".queue", false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange("users.exchange");
    }

    @Bean
    Binding firstBinding(Queue firstUserQueue, DirectExchange exchange) {
        return BindingBuilder
                .bind(firstUserQueue)
                .to(exchange)
                .with(userName); // routing key
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitMqReceiver rabbitMqReceiver() {
        return new RabbitMqReceiver();
    }
}