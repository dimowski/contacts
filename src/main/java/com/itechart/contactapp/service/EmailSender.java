package com.itechart.contactapp.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {

    private static final Logger log = LogManager.getLogger(EmailSender.class);

    static Session getMailSession;
    static MimeMessage generateMailMessage;

    public static void generateAndSendEmail(Properties mailServerProperties, String emailRecipient, String subject, String emailBody) throws MessagingException {
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailRecipient));
        generateMailMessage.setSubject(subject, "UTF-8");
        generateMailMessage.setContent(emailBody, "text/plain; charset=UTF-8");
        log.info("Mail Session has been created successfully..");

        Transport transport = getMailSession.getTransport("smtp");

        // Enter your correct gmail UserID and Password
        // if you have 2FA enabled then provide App Specific Password
        String host = mailServerProperties.getProperty("mail.smtp.host");
        String sendFrom = mailServerProperties.getProperty("mail.send.from");
        String pass = mailServerProperties.getProperty("mail.send.password");
        transport.connect(host, sendFrom, pass);
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }
}