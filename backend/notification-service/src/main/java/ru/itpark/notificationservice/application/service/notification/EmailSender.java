package ru.itpark.notificationservice.application.service.notification;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itpark.notificationservice.infrastructure.kafka.InvitationMessage;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

import static jakarta.mail.Message.RecipientType.TO;

@Service
public class EmailSender {

    @Value("${notification.emails.server}")
    private String emailServer;
    @Value("${notification.emails.password}")
    private String pass;

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final NetHttpTransport HTTP_TRANSPORT;

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @SneakyThrows
    public void sendEmail(InvitationMessage invitationMessage) {

        String host = "smtp.mail.ru";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "465");
        props.put("mail.smtps.ssl.checkserveridentity", true);
        props.put("mail.smtps.ssl.trust", "*");
        props.put("mail.smtp.ssl.enable", "true");

        InternetAddress[] receivers = new InternetAddress[1];
        receivers[0] = new InternetAddress(invitationMessage.getInvitedUserEmail());

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailServer, pass);
            }
        });

        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(emailServer, false));

        msg.setRecipients(TO, receivers);
        msg.setSubject(invitationMessage.getType());
        msg.setContent(invitationMessage.getInvitationMessage(), "text/html;charset=utf-8");
        msg.setSentDate(new Date());
        msg.setReplyTo(new Address[]{new InternetAddress("no-reply@example.com", false)});

        Transport.send(msg);
    }

}
