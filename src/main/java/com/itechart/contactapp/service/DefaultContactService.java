package com.itechart.contactapp.service;

import com.itechart.contactapp.dao.ContactDAO;
import com.itechart.contactapp.dao.ContactDAOFactory;
import com.itechart.contactapp.model.Contact;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.Map;

public class DefaultContactService implements ContactService {

    private static final Logger log = LogManager.getLogger(DefaultContactService.class);

    private ContactDAO contactDAO;

    public DefaultContactService(DataSource theDataSource) {
        contactDAO = ContactDAOFactory.getContactDAO(theDataSource);
    }

    @Override
    public void listContacts(HttpServletRequest request, HttpServletResponse response) {
        int targetPage = 1;
        String targetPageParam = request.getParameter("targetPage");
        if (targetPageParam != null) {
            switch (targetPageParam) {
                case "next":
                    targetPage = ((int) request.getSession().getAttribute("CURRENT_PAGE")) + 1;
                    break;
                case "prev":
                    targetPage = ((int) request.getSession().getAttribute("CURRENT_PAGE") - 1);
                    break;
                default:
                    targetPage = Integer.parseInt(targetPageParam);
                    break;
            }
        }

        Map<Integer, Contact> contacts = contactDAO.getContacts(targetPage);

        int contactsCount = getContactsCount();
        int pagesCount = (int) Math.ceil(contactsCount / 10.0);

        request.setAttribute("CONTACTS_COUNT", contactsCount);
        request.setAttribute("PAGES_COUNT", pagesCount);
        request.getSession().setAttribute("CURRENT_PAGE", targetPage);
        request.getSession().setAttribute("CONTACT_LIST", contacts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-contact.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            log.error(e);
        }
    }

    @Override
    public void deleteContact(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public void fillContact(HttpServletRequest request, HttpServletResponse response) {
        int contactId = Integer.parseInt(request.getParameter("contactId"));
        log.debug("contactId = {}", contactId);

        Map<Integer, Contact> tempList = (Map<Integer, Contact>) request.getSession().getAttribute("CONTACT_LIST");

        Contact theContact = tempList.get(contactId);
        log.debug(theContact);
        theContact.setPhones(contactDAO.getPhonesByContactId(contactId));
        theContact.setAttachments(contactDAO.getAttachmentsByContactId(contactId));

        request.setAttribute("CONTACT", theContact);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/edit-contact.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            log.error(e);
        }
    }

    @Override
    public void saveContact(HttpServletRequest request, HttpServletResponse response) {
    }

    @Override
    public int getContactsCount() {
        return contactDAO.getContactsCount();
    }
}
