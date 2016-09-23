package com.itechart.contactapp.command;

import com.itechart.contactapp.dao.ContactDAO;
import com.itechart.contactapp.dao.ContactDAOFactory;
import com.itechart.contactapp.model.Contact;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.Map;

public class EditContactCommand implements Command {

    private static final Logger log = LogManager.getLogger(EditContactCommand.class);

    //private ContactService contactService = ContactServiceFactory.getContactService();
    private ContactDAO contactDAO;

    public EditContactCommand(DataSource dataSource) {
        contactDAO = ContactDAOFactory.getContactDAO(dataSource);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int contactId = Integer.parseInt(request.getParameter("contactId"));
        log.debug("Edit contact with id = {}", contactId);

        Map<Integer, Contact> tempList = (Map<Integer, Contact>) request.getSession().getAttribute("CONTACT_LIST");

        Contact theContact = tempList.get(contactId);
        theContact.setPhones(contactDAO.getPhonesByContactId(contactId));
        theContact.setAttachments(contactDAO.getAttachmentsByContactId(contactId));

        request.getSession().setAttribute("CONTACT", theContact);
        return "/edit-contact.jsp";
    }
}
