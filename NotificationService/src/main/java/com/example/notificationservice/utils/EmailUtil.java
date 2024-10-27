package com.example.notificationservice.utils;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.logging.Logger;


@Component
public class EmailUtil {

    private static final Logger logger = Logger.getLogger(EmailUtil.class.getName());


    public void sendEmail(Session session, String toEmail, String subject, String body) {
        try {
            MimeMessage message = getMimeMessage(session, subject, body);

            // Set email recipient TO field
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

            // Send the email
            Transport.send(message);
            
            logger.info("Email sent successfully to: " + toEmail);
        }
        catch (Exception e) {
            logger.severe("Exception occurred while sending email: " + e.getMessage());
        }
    }


    private MimeMessage getMimeMessage(Session session, String subject, String body) throws MessagingException {
        MimeMessage message = new MimeMessage(session);

        // Set message headers
        message.addHeader("Content-type", "text/HTML; charset=UTF-8");
        message.addHeader("format", "flowed");
        message.addHeader("Content-Transfer-Encoding", "8bit");

        // Set mail subject
        message.setSubject(subject, "UTF-8");

        // Set mail body
        message.setText(body, "UTF-8");

        // Set mail sent date
        message.setSentDate(new Date());

        return message;
    }

}

