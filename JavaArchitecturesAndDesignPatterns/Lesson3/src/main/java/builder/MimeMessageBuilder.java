package builder;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

public class MimeMessageBuilder {
    private final MimeMessage message;

    public MimeMessageBuilder(Session session) {
        message = new MimeMessage(session);
    }

    public MimeMessageBuilder from(String address) throws MessagingException {
        message.setFrom(address);
        return this;
    }

    public MimeMessageBuilder to(String address) throws MessagingException {
        message.setRecipients(Message.RecipientType.TO, address);
        return this;
    }

    public MimeMessageBuilder cc(String address) throws MessagingException {
        message.setRecipients(Message.RecipientType.CC, address);
        return this;
    }

    public MimeMessageBuilder subject(String subject) throws MessagingException {
        message.setSubject(subject);
        return this;
    }

    public MimeMessageBuilder body(String body) throws MessagingException {
        message.setText(body);
        return this;
    }

    public MimeMessage build() {
        return message;
    }

}
