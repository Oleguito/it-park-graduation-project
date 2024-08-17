package ru.itpark.notificationservice.application.service.notification;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itpark.notificationservice.infrastructure.kafka.InvitationMessage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.InetAddress;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Service
public class EmailSender {
    @Value("${notification.emails.server}")
    private String emailServer;
    @Value("${notification.emails.password}")
    private String pass;

    @SneakyThrows
    public void sendEmail(InvitationMessage invitationMessage) {

        InternetAddress[] receivers = new InternetAddress[1];
        receivers[0] = new InternetAddress(invitationMessage.getInvitedUserEmail());

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(emailServer, pass);
                return new PasswordAuthentication("spr1tee1337@gmail.com", "wkwr ndzp dznk jpdy");
            }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(emailServer, false));

        msg.setRecipients(Message.RecipientType.TO, receivers);
        msg.setSubject(invitationMessage.getType());
        msg.setContent(invitationMessage.getInvitationMessage(), "text/html;charset=utf-8");
        msg.setSentDate(new Date());
        msg.setReplyTo(new Address[]{new InternetAddress("no-reply@example.com", false)});

        Transport.send(msg);

    }

}
