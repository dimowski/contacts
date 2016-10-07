package com.itechart.contactapp.command;

import com.itechart.contactapp.mailing.EmailLetter;
import com.itechart.contactapp.mailing.EmailSender;
import com.itechart.contactapp.model.Contact;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stringtemplate.v4.ST;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class SendEmailCommand implements Command {

    private static final Logger log = LogManager.getLogger(SendEmailCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Sending email...");
        String emails = request.getParameter("emails");
        if (StringUtils.isNotEmpty(emails)) {
            Properties properties = (Properties) request.getServletContext().getAttribute("mailing.properties");
            String subject = request.getParameter("subject");
            String emailBody = request.getParameter("emailBody");

            List<String> idList = (List<String>) request.getSession().getAttribute("idForMailing");
            request.getSession().removeAttribute("idForMailing");
            Map<Integer, Contact> contactMap = (Map<Integer, Contact>) request.getSession().getAttribute("CONTACT_LIST");
            new Thread(() -> {
                List<EmailLetter> letterList = new ArrayList<>();
                Contact tempContact = null;
                ST st = null;
                for (String id : idList) {
                    st = new ST(emailBody);
                    tempContact = contactMap.get(Integer.parseInt(id));
                    st.add("contact", tempContact);
                    letterList.add(new EmailLetter(tempContact.getEmail(), subject, st.render()));
                }
                try {
                    EmailSender.generateAndSendEmail(properties, letterList);
                } catch (MessagingException e) {
                    log.error("Error while sending email", e);
                }
            }).start();
            log.debug("Mail has been sent");
        }
        return "/mailing-status.jsp";
    }
}