package com.itechart.contactapp.command;

import com.itechart.contactapp.dao.AttachmentDAO;
import com.itechart.contactapp.dao.AttachmentDAOFactory;
import com.itechart.contactapp.dao.PhoneDAO;
import com.itechart.contactapp.dao.PhoneDAOFactory;
import com.itechart.contactapp.model.Contact;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.Map;

public class EditContactCommand implements Command {

    private static final Logger log = LogManager.getLogger(EditContactCommand.class);

    private PhoneDAO phoneDAO;
    private AttachmentDAO attachmentDAO;

    public EditContactCommand(DataSource dataSource) {
        phoneDAO = PhoneDAOFactory.getPhoneDAO(dataSource);
        attachmentDAO = AttachmentDAOFactory.getAttachmentDAO(dataSource);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int contactId = Integer.parseInt(request.getParameter("contactId"));
        log.debug("Edit contact with id = {}", contactId);

        Map<Integer, Contact> tempList = (Map<Integer, Contact>) request.getSession().getAttribute("CONTACT_LIST");

        Contact theContact = tempList.get(contactId);
        theContact.setPhones(phoneDAO.getPhonesByContactId(contactId));
        theContact.setAttachments(attachmentDAO.getAttachmentsByContactId(contactId));

        request.getSession().setAttribute("CONTACT", theContact);
        return "/edit-contact.jsp";
    }
}
