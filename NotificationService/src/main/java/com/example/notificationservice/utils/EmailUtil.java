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


    // This method is used to send email to the specified email address of the user
    public void sendEmail(Session session, String toEmail, String subject, String body) {
        try {
            MimeMessage message = getMimeMessage(session, subject, body);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));  // Set email recipient TO field
            Transport.send(message);  // Send the email
            logger.info("Email sent successfully to: " + toEmail);
        }
        catch (Exception e) {
            logger.severe("Exception occurred while sending email: " + e.getMessage());
        }
    }


    // This method is used to create a MimeMessage object
    private MimeMessage getMimeMessage(Session session, String subject, String body) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        // Set message headers
        message.addHeader("Content-type", "text/HTML; charset=UTF-8");
        message.addHeader("format", "flowed");
        message.addHeader("Content-Transfer-Encoding", "8bit");

        message.setSubject(subject, "UTF-8");  // Set mail subject
        message.setText(body, "UTF-8");  // Set mail body
        message.setSentDate(new Date());  // Set mail sent date
        return message;
    }

}

