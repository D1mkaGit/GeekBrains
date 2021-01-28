package builder;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailClient {

    public static void main(String[] args) {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "mail.hot.ee");
        Session session = Session.getInstance(props);
        String sender = "test@test.ee";
        String receiver = "d1mka@msn.com";
        String test_subject = "test subject";
        String test_message = "test message";
        try {
            new MailClient().sendMail(session, sender, receiver, test_subject, test_message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendMail(Session session, String sender, String receiver, String subject, String messageText) throws MessagingException {
        MimeMessage message = (new MimeMessageBuilder(session))
                .from(sender)
                .to(receiver)
                .subject(subject)
                .body(messageText)
                .build();

        Transport.send(message);
    }
}
