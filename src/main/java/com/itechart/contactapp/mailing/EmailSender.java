package com.itechart.contactapp.mailing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class EmailSender {

    private static final Logger log = LogManager.getLogger(EmailSender.class);

    static Session mailSession;
    static MimeMessage generateMailMessage;

    public static void generateAndSendEmail(Properties mailServerProperties, List<EmailLetter> letterList) throws MessagingException {
        mailSession = Session.getDefaultInstance(mailServerProperties);
        Transport transport = mailSession.getTransport("smtp");

        // Enter your correct gmail UserID and Password
        // if you have 2FA enabled then provide App Specific Password
        String host = mailServerProperties.getProperty("mail.smtp.host");
        String sendFrom = mailServerProperties.getProperty("mail.send.from");
        String pass = mailServerProperties.getProperty("mail.send.password");
        transport.connect(host, sendFrom, pass);
        log.info("Preparing to send {} emails", letterList.size());
        for (EmailLetter letter : letterList) {
            generateMailMessage = new MimeMessage(mailSession);
            generateMailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(letter.getEmailRecipient()));
            generateMailMessage.setSubject(letter.getEmailSubject(), "UTF-8");
            generateMailMessage.setContent(letter.getMessageBody(), "text/plain; charset=UTF-8");
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        }
        transport.close();
    }
}