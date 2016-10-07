package com.itechart.contactapp.mailing;

import com.itechart.contactapp.dao.ContactDAO;
import com.itechart.contactapp.dao.ContactDAOFactory;
import com.itechart.contactapp.model.Contact;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import javax.mail.MessagingException;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DailyMailingJob implements Runnable {

    private static final Logger log = LogManager.getLogger(DailyMailingJob.class);
    private DataSource dataSource;
    private Properties properties;

    public DailyMailingJob(DataSource dataSource, Properties properties) {
        this.properties = properties;
        this.dataSource = dataSource;
    }

    @Override
    public void run() {
        log.info("Checking contacts birthday");
        ContactDAO contactDAO = ContactDAOFactory.getContactDAO(dataSource);
        List<Contact> contacts = contactDAO.getContactsByTodayBirthday();
        log.debug("Today is birthday for {} contacts", contacts.size());
        try {
            if (!CollectionUtils.isEmpty(contacts)) {
                STGroup group = new STGroupFile("birthdayMailing.stg");
                ST s = group.getInstanceOf("birthday");
                s.add("contacts", contacts);
                List<EmailLetter> letterList = new ArrayList<EmailLetter>() {{
                    add(new EmailLetter(properties.getProperty("mail.admin.address"), "Birthday celebration", s.render()));
                }};
                EmailSender.generateAndSendEmail(properties, letterList);
            } else {
                STGroup group = new STGroupFile("birthdayMailing.stg");
                ST s = group.getInstanceOf("emptyBirthday");
                List<EmailLetter> letterList = new ArrayList<EmailLetter>() {{
                    add(new EmailLetter(properties.getProperty("mail.admin.address"), "No birthday today", s.render()));
                }};
                EmailSender.generateAndSendEmail(properties, letterList);
            }
        } catch (MessagingException e) {
            log.error(e);
        }
    }
}