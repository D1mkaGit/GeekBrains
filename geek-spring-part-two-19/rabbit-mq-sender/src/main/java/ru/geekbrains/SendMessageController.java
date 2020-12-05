package ru.geekbrains;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.repr.TextMessage;

@RestController
public class SendMessageController {

    private final AmqpTemplate rabbitTemplate;

    @Value("${user.name}")
    private String userName;

    @Autowired
    public SendMessageController(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("send")
    public String sendMessage(@RequestParam("user") String user,
                              @RequestBody String msg) {
        rabbitTemplate.convertAndSend("users.exchange", user, new TextMessage(userName, msg));
        return "Message '" + msg + "' sent from user '" + userName + "' to user '" + user + "' with RabbitMQ";
    }
}
