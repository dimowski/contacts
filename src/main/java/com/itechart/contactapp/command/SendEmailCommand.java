package com.itechart.contactapp.command;

import com.itechart.contactapp.model.Contact;
import com.itechart.contactapp.mailing.EmailSender;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stringtemplate.v4.ST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            Map<Integer, Contact> contactMap = (Map<Integer, Contact>) request.getSession().getAttribute("CONTACT_LIST");
            new Thread(() -> {
                try {
                    for (String id : idList) {
                        ST st = new ST(emailBody);
                        Contact tempContact = contactMap.get(Integer.parseInt(id));
                        st.add("contact", tempContact);
                        log.debug("idList size {}", idList.size());
                        EmailSender.generateAndSendEmail(properties, tempContact.getEmail(), subject, st.render());
                    }
                } catch (Exception e) {
                    log.error(e);
                }
            }).start();
        }
        return "/mailing-status.jsp";
    }
}